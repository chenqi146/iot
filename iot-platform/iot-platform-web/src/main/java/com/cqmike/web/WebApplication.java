package com.cqmike.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 公共模块
 * @author 陈琪
 */
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.cqmike"})
public class WebApplication {

    @Value("${spring.redis.feign-token}")
    private String token;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("feignToken", token);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
