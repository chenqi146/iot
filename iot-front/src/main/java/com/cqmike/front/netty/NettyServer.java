package com.cqmike.front.netty;

import com.cqmike.front.netty.decoder.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: iot
 * @ClassName: NettyServer
 * @Description: netty服务端
 * @Author: chen qi
 * @Date: 2020/3/7 9:42
 * @Version: 1.0
 **/
@Slf4j
@Component
public class NettyServer {

    private static class SingletonNettyServer {
        static final NettyServer INSTANCE = new NettyServer();
    }

    public static NettyServer getInstance() {
        return SingletonNettyServer.INSTANCE;
    }

    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    private final ServerBootstrap bootstrap = new ServerBootstrap();

    private Channel channel;

    public NettyServer() {
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new NettyServerInitializer());

    }

    public void start() {
        try {
            ChannelFuture channelFuture = bootstrap.bind(8088).sync();
            channel = channelFuture.channel();
            channelFuture.channel().closeFuture().sync().addListener((ChannelFutureListener) channelFuture1 -> {
                log.info(channelFuture1.channel().toString() + "链路关闭");
                if (channel != null) {
                    channel.close();
                }
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            });
        } catch (InterruptedException e) {
            log.error("Netty启动异常", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
