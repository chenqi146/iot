package com.cqmike.mock.netty;

import cn.hutool.core.collection.CollUtil;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.core.result.ReturnForm;
import com.cqmike.mock.client.PlatformClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

/**
 * @program: iot
 * @ClassName: NettyClient
 * @Description: netty客户端
 * @Author: chen qi
 * @Date: 2020/3/25 17:36
 * @Version: 1.0
 **/
@Component
public class NettyClient implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(NettyClient.class);

    @Value("${mock.netty.server.host}")
    private String host;

    @Value("${mock.netty.server.port}")
    private Integer port;

    @Value("${mock.netty.server.count}")
    private Integer count;

    @Resource
    private PlatformClient platformClient;

    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    public static final Bootstrap bootstrap = new Bootstrap();

    public void start() throws InterruptedException {
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringEncoder());
//                ch.pipeline().addLast(new MessageToByteEncoder<String>() {
//                    @Override
//                    protected void encode(ChannelHandlerContext channelHandlerContext, String s, ByteBuf byteBuf) {
//                        byteBuf.writeBytes(s.getBytes());
//                        byteBuf.writeBytes(Unpooled.copiedBuffer("\r\n".getBytes()));
//                    }
//                });
            }
        });

        ReturnForm<List<DeviceForm>> allDeviceList = platformClient.findAllDeviceList();
        List<DeviceForm> message = allDeviceList.getMessage();
        if (CollUtil.isEmpty(message)) {
            log.error("没有设备" );
        }

        for (DeviceForm deviceForm : message) {
            ChannelFuture future = bootstrap.connect(host, port).sync();
            ByteBuf byteBuf = Unpooled.buffer();
            byteBuf.writeBytes(deviceForm.getSn().getBytes());
            byteBuf.writeBytes(Unpooled.copiedBuffer("\r\n".getBytes()));
            future.channel().writeAndFlush(byteBuf);
            ChannelMap.put(deviceForm.getSn(), new MockChannel(future.channel()));
        }
        log.info("启动成功 {}:{}", host, port);
    }


    @Override
    public void run(String... args) throws Exception {
        this.start();
    }

    @PreDestroy
    public void stop() throws InterruptedException {
        workerGroup.shutdownGracefully().sync();
        List<MockChannel> values = ChannelMap.values();
        for (MockChannel value : values) {
            value.getChannel().closeFuture().sync();
        }
    }
}
