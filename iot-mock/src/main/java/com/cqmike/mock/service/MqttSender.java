package com.cqmike.mock.service;

import com.cqmike.mock.config.MqttReceiveConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @program: iot
 * @Interface: MqttSender
 * @Description: mqtt发送
 * @Author: chen qi
 * @Date: 2020/3/3 22:09
 * @Version: 1.0
 **/
@Component
@MessagingGateway(defaultRequestChannel = MqttReceiveConfig.CHANNEL_NAME_OUT)
public interface MqttSender {

    Logger log = LoggerFactory.getLogger(MqttSender.class);
    /**
     * 发送信息到MQTT服务器
     * @param data
     */
    void sendToMqtt(String data);


    /**
     * 发送信息到MQTT服务器
     *
     * @param topic 主题
     * @param payload 消息主体
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic,
                    String payload);

    /**
     * 发送信息到MQTT服务器
     *
     * @param topic 主题
     * @param qos 对消息处理的几种机制。
     * 0 表示的是订阅者没收到消息不会再次发送，消息会丢失。
     * 1 表示的是会尝试重试，一直到接收到消息，但这种情况可能导致订阅者收到多次重复消息。
     * 2 多了一次去重的动作，确保订阅者收到的消息有一次。
     * @param payload 消息主体
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic,
                    @Header(MqttHeaders.QOS) int qos,
                    String payload);

}


