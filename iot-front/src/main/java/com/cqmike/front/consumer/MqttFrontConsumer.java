package com.cqmike.front.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @program: iot
 * @ClassName: MqttFrontConsumer
 * @Description: mqtt接收类
 * @Author: chen qi
 * @Date: 2020/3/24 20:13
 * @Version: 1.0
 **/
@Slf4j
@Component
public class MqttFrontConsumer {

    /**
     *  获取topic的key
     **/
    private final static String TOPIC_KEY = "mqtt_receivedTopic";

    @Bean
    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public MessageHandler handler() {
        return message -> {
            String topic = Objects.requireNonNull(message.getHeaders().get(TOPIC_KEY)).toString();
            String payload = String.valueOf(message.getPayload());
            log.info("topic: ({}), data: ({})", topic, payload);
            //todo 处理消息  mqtt连接设备解析 组织好的json
        };
    }
}
