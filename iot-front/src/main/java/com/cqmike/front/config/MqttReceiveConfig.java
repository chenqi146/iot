package com.cqmike.front.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

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

    private static final Logger log = LoggerFactory.getLogger(MqttReceiveConfig.class);

    public final static String CHANNEL_NAME_OUT = "mqttOutboundChannel";

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
        mqttConnectOptions.setMaxInflight(1000);
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
    public MessageChannel mqttInboundChannel() {
        return new DirectChannel();
    }

    /**
     * MQTT信息通道（生产者）
     */
    @Bean(name = CHANNEL_NAME_OUT)
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /**
     * MQTT消息处理器（生产者）
     */
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_OUT)
    public MessageHandler outbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                mqttConfig.getClientId() + "_outbound",
                this.mqttPahoClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(mqttConfig.getDefaultSenderTopic());
        return messageHandler;
    }

    /**
     *     服务端配置client,监听的topic  消费者
     */
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter
                = new MqttPahoMessageDrivenChannelAdapter(mqttConfig.getClientId() + "_inbound",
                this.mqttPahoClientFactory(), mqttConfig.getDefaultReceiveTopic());
        adapter.setCompletionTimeout(mqttConfig.getCompletionTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(this.mqttInboundChannel());
        return adapter;
    }

}
