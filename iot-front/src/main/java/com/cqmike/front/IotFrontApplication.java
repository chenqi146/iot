package com.cqmike.front;

import com.cqmike.front.netty.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chen qi
 */
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class IotFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotFrontApplication.class, args);
        NettyServer.getInstance().start();
    }

}
