package com.cqmike.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IotMockApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotMockApplication.class, args);
    }

}
