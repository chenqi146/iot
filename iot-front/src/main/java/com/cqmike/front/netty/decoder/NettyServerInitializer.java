package com.cqmike.front.netty.decoder;

import com.cqmike.front.netty.HeartbeatServerHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @program: iot
 * @ClassName: ServerChannelInitializer
 * @Description: netty通道初始化类
 * @Author: chen qi
 * @Date: 2020/3/7 10:19
 * @Version: 1.0
 **/
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 拆包粘包统一由硬件发送数据末端携带\r\n符号   如果手动处理半包粘包需要报文长度字段
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024 * 2, Unpooled.copiedBuffer("\r\n".getBytes())));
        // 空闲检测
        pipeline.addLast(new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS));
        // 心跳处理
        pipeline.addLast(new HeartbeatServerHandler());
        // 注册
        pipeline.addLast(new RegisterDecoder());
        // 解析
        pipeline.addLast(new AnalyseDecoder());
        // 分发
        pipeline.addLast(new DistributionDecoder());
    }
}
