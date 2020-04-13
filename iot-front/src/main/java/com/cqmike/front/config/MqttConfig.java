package com.cqmike.front.config;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDefaultReceiveTopic() {
        return defaultReceiveTopic;
    }

    public void setDefaultReceiveTopic(String defaultReceiveTopic) {
        this.defaultReceiveTopic = defaultReceiveTopic;
    }

    public String getDefaultSenderTopic() {
        return defaultSenderTopic;
    }

    public void setDefaultSenderTopic(String defaultSenderTopic) {
        this.defaultSenderTopic = defaultSenderTopic;
    }

    public Long getCompletionTimeout() {
        return completionTimeout;
    }

    public void setCompletionTimeout(Long completionTimeout) {
        this.completionTimeout = completionTimeout;
    }
}
