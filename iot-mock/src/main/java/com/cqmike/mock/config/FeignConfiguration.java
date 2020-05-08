package com.cqmike.mock.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @program: iot
 * @ClassName: FeignConfiguration
 * @Description: feign配置
 * @Author: chen qi
 * @Date: 2020/3/30 20:27
 * @Version: 1.0
 **/
@Configuration
public class FeignConfiguration {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 日志级别
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(5, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true);
    }

    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String feignToken = ops.get("feignToken");
        return new FeignBasicAuthRequestInterceptor(feignToken);
    }
}
