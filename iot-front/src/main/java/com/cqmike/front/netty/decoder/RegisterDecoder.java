package com.cqmike.front.netty.decoder;

import cn.hutool.core.util.HexUtil;
import com.cqmike.asset.form.front.DeviceFormForFront;
import com.cqmike.front.netty.Connection;
import com.cqmike.front.netty.Const;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.script.ScriptException;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @program: iot
 * @ClassName: RegisterDecoder
 * @Description: 注册类
 * @Author: chen qi
 * @Date: 2020/3/7 10:50
 * @Version: 1.0
 **/
@Slf4j
public class RegisterDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> in) throws ScriptException {

        byteBuf.retain();
        int readableBytesLength = byteBuf.readableBytes();
        byte[] s = new byte[readableBytesLength];
        byteBuf.getBytes(0, s);
        System.out.println(new String(s));

        Connection connection = ctx.channel().attr(Const.CONNECTION).get();
        if (connection == null) {

            // 处理空包
            if (readableBytesLength <= 0) {
                return;
            }

            byte[] bytes = new byte[readableBytesLength];
            byteBuf.readBytes(bytes);
            String deviceSn = HexUtil.encodeHexStr(bytes);
            if (StringUtils.isEmpty(deviceSn)) {
                return;
            }

            //todo 平台接口获取该sn对应的设备
            connection = new Connection(ctx.channel(), deviceSn, new DeviceFormForFront());
            connection.register(connection);
            log.info("注册成功————ChannelId为{}, deviceSn为{}。", connection.getChannel().id(), deviceSn);
        } else {
            ByteBuf buf = byteBuf.retainedDuplicate();
            in.add(buf);
            byteBuf.skipBytes(readableBytesLength);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketAddr = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = socketAddr.getAddress().getHostAddress();
        log.info("来自{}的客户端断开连接:  ChannelId为: {}", clientIp, ctx.channel().id());
        Connection conn = ctx.channel().attr(Const.CONNECTION).get();
        if (conn == null) {
            super.channelInactive(ctx);
            return;
        }
        conn.logOut();
        //todo 设备离线处理  kafka推给平台端  平台端websocket推给前端
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        InetSocketAddress socketAddr = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = socketAddr.getAddress().getHostAddress();
        log.info("来自{}的客户端建立连接:  ChannelId为: {}", clientIp, ctx.channel().id());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("Netty出现Throwable, 异常信息: {}", cause.getMessage());
        ctx.channel().close();
    }
}
