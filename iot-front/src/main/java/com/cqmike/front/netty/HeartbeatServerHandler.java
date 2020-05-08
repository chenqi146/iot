package com.cqmike.front.netty;

import com.cqmike.front.map.Connection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: iot
 * @ClassName: HeartbeatServerHandler
 * @Description: 心跳处理类
 * @Author: chen qi
 * @Date: 2020/3/7 10:48
 * @Version: 1.0
 **/
public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(HeartbeatServerHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Connection connection = ctx.channel().attr(Const.CONNECTION).get();
        if (connection == null) {
            log.info("该ChannelId为: ({})的通道没有进行注册, 将断开连接!", ctx.channel().id());
            ctx.disconnect();
            return;
        }
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                ctx.disconnect();
                log.info("ChannelId为: ({}), 编号为: ({})的设备在规定时间内没有数据上报, 将断开连接!",connection.getChannel().id(), connection.getDeviceSn());
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
