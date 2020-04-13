package com.cqmike.front.netty.decoder;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import com.cqmike.common.front.form.DeviceFormForFront;
import com.cqmike.core.result.ReturnForm;
import com.cqmike.front.client.PlatformClient;
import com.cqmike.front.map.Connection;
import com.cqmike.front.netty.Const;
import com.cqmike.front.service.KafkaService;
import com.cqmike.front.util.SpringContextUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class RegisterDecoder extends ByteToMessageDecoder {

    private static final Logger log = LoggerFactory.getLogger(RegisterDecoder.class);

    private PlatformClient platformClient = SpringContextUtil.getBean(PlatformClient.class);
    private KafkaService kafkaService = SpringContextUtil.getBean(KafkaService.class);
    private static final String DEVICE_OFFLINE = "deviceOffline";
    private static final String DEVICE_ONLINE = "deviceOnline";

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> in) {

        byteBuf.retain();
        int readableBytesLength = byteBuf.readableBytes();
        byte[] s = new byte[readableBytesLength];
        byteBuf.getBytes(0, s);

        Connection connection = ctx.channel().attr(Const.CONNECTION).get();
        if (connection == null) {

            // 处理空包
            if (readableBytesLength <= 0) {
                return;
            }

            byte[] bytes = new byte[readableBytesLength];
            byteBuf.readBytes(bytes);
            String deviceSn = HexUtil.encodeHexStr(bytes);
            if (StrUtil.isEmpty(deviceSn)) {
                return;
            }

            ReturnForm<DeviceFormForFront> returnForm = platformClient.findDeviceForFrontBySn(deviceSn);
            DeviceFormForFront deviceForFront = returnForm.getMessage();
            if (deviceForFront == null) {
                log.error("deviceSn(({}))没有对应的设备信息", deviceSn);
                return;
            }
            connection = new Connection(ctx.channel(), deviceSn, deviceForFront);
            connection.register(connection);
            log.info("注册成功————ChannelId为({}), deviceSn为({})。", connection.getChannel().id(), deviceSn);
        } else {
            ByteBuf buf = byteBuf.retainedDuplicate();
            in.add(buf);
            byteBuf.skipBytes(readableBytesLength);
        }
        kafkaService.asyncSendDataToKafkaTopic(DEVICE_ONLINE, connection.getDeviceSn());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketAddr = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = socketAddr.getAddress().getHostAddress();
        log.info("来自({})的客户端断开连接:  ChannelId为: ({})", clientIp, ctx.channel().id());
        Connection conn = ctx.channel().attr(Const.CONNECTION).get();
        if (conn == null) {
            super.channelInactive(ctx);
            return;
        }
        conn.logOut();
        kafkaService.asyncSendDataToKafkaTopic(DEVICE_OFFLINE, conn.getDeviceSn());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        InetSocketAddress socketAddr = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = socketAddr.getAddress().getHostAddress();
        log.info("来自({})的客户端建立连接:  ChannelId为: ({})", clientIp, ctx.channel().id());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("Netty出现Throwable, 异常信息: ({})", cause.getMessage());
        ctx.channel().close();
    }
}
