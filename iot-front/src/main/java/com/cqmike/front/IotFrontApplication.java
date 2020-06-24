package com.cqmike.front;

import com.cqmike.front.netty.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

/**
 * @author chen qi
 */
@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IotFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotFrontApplication.class, args);
        // 在spring启动之后启动   不阻塞主线程
        NettyServer.getInstance().start();
    }

    /**
     * 有启动就有关闭嘛
     * @throws InterruptedException
     */
    @PreDestroy
    public void stop() throws InterruptedException {
        NettyServer.getInstance().stop();
    }
}
