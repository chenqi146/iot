package com.cqmike.front.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: iot
 * @ClassName: MqttConfig
 * @Description: mqtt属性类
 * @Author: chen qi
 * @Date: 2020/3/2 21:52
 * @Version: 1.0
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {


    /**
     * mqtt用户名
    **/
    private String username;
    
    /**
     * 密码
    **/
    private String password;
    
    /**
     * host
    **/
    private String host;
    
    /**
     * 客户端clientId
    **/
    private String clientId;
    
    /**
     * 默认的topic，可指定订阅或推送
    **/
    private String defaultReceiveTopic;

    /**
     * 默认的topic，可指定订阅或推送
     **/
    private String defaultSenderTopic;
    
    /**
     * 超时时间
    **/
    private Long completionTimeout;


}
