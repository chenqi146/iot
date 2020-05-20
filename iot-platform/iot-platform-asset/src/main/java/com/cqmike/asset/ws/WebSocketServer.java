package com.cqmike.asset.ws;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.cqmike.common.dto.SocketMessage;
import com.cqmike.core.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @program: iot
 * @ClassName: WebSocketServer
 * @Description: WebSocketServer
 * @Author: chen qi
 * @Date: 2020/5/20 13:36
 * @Version: 1.0
 **/
@Component
@ServerEndpoint("/ws/device/{sn}")
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    /**
     * concurrent包的线程安全Set, 用来存放每个客户端对应的MyWebSocket对象。
     */
    private final static ConcurrentHashMap<String, WebSocketServer> WEBSOCKET_MAP = MapUtil.newConcurrentHashMap();
    /**
     * 静态变量, 用来记录当前在线连接数。
     */
    private static int onlineCount = 0;
    /**
     * 与某个客户端的连接会话, 需要通过它来给客户端发送数据
     */
    private Session session;

    private String sn;

    /**
     * 向指定应用下的设备推送消息
     *
     * @param message
     */
    public static void sendMessage(String sn, SocketMessage message) {
        String msg = JsonUtils.toJson(message);
        WEBSOCKET_MAP.forEach((k, v) -> {
            if (k.startsWith(sn)) {
                try {
                    v.sendMessage(msg);
                } catch (IOException e) {
                    log.error("webSocket发送消息失败, sn: [{}], message: [{}]", sn, message, e);
                }
            }
        });
    }

    public static ConcurrentHashMap<String, WebSocketServer> getWebSocketMap() {
        return WEBSOCKET_MAP;
    }

    public static synchronized int getOnlineCount() {
        return WebSocketServer.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("sn") String sn) {
        this.session = session;
        this.sn = sn + StrUtil.UNDERLINE + session.getId();
        WEBSOCKET_MAP.put(this.sn, this);
        addOnlineCount();
        log.info("client: {}, ip: {} 接入连接", this.sn, session.getRequestURI().getHost());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info(message);
    }

    @OnClose
    public void onClose() {
        WEBSOCKET_MAP.remove(this.sn);
        subOnlineCount();
        log.info("client: {}, ip: {} 断开连接", this.sn, session.getRequestURI().getHost());
    }

    @OnError
    public void onError(Session session, Throwable e) {
        log.error("webSocket报错, sn: [{}]", sn, e);
    }

    /**
     * 向所有连接推送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
