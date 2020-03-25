package com.cqmike.mock.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * @program: iot
 * @ClassName: NettyClient
 * @Description: netty客户端
 * @Author: chen qi
 * @Date: 2020/3/25 17:36
 * @Version: 1.0
 **/
@Slf4j
public class NettyClient {

    @Value("${mock.netty.server.host}")
    private String host;

    @Value("${mock.netty.server.port}")
    private Integer port;

    @Value("${mock.netty.server.count}")
    private Integer count;

    private static class SingletonNettyClient {
        static final NettyClient INSTANCE = new NettyClient();
    }

    public static NettyClient getInstance() {
        return SingletonNettyClient.INSTANCE;
    }

    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    private final Bootstrap bootstrap = new Bootstrap();


    public void start() {
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
            }
        });

        for (int i = 0; i < count; i++) {
            ChannelFuture future = bootstrap.connect(host, port);
            ChannelMap.put("", future.channel());
        }

    }


}
