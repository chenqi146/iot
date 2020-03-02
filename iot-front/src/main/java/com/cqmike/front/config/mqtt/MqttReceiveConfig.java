package com.cqmike.front.config.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.Objects;

/**
 * @program: iot
 * @ClassName: MqttSenderConfig
 * @Description: mqtt接收类
 * @Author: chen qi
 * @Date: 2020/3/2 21:50
 * @Version: 1.0
 **/
@Configuration
public class MqttReceiveConfig {

    /**
     *  获取topic的key
    **/
    private final static String TOPIC_KEY = "mqtt_receivedTopic";

    private final MqttConfig mqttConfig;

    public MqttReceiveConfig(MqttConfig mqttConfig) {
        this.mqttConfig = mqttConfig;
    }

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(mqttConfig.getUsername());
        mqttConnectOptions.setPassword(mqttConfig.getPassword().toCharArray());
        String[] split = mqttConfig.getHost().split(",");
        mqttConnectOptions.setServerURIs(split);
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(this.getMqttConnectOptions());
        return factory;
    }

    /**
     *     接收通道
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     *     配置client,监听的topic
     */
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter
                = new MqttPahoMessageDrivenChannelAdapter(mqttConfig.getClientId() + "_inbound",
                this.mqttPahoClientFactory(), mqttConfig.getDefaultTopic());
        adapter.setCompletionTimeout(mqttConfig.getCompletionTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(this.mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            String topic = Objects.requireNonNull(message.getHeaders().get(TOPIC_KEY)).toString();
            //todo 处理消息
        };
    }
}
