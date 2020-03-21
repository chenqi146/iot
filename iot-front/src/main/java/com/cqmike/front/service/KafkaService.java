package com.cqmike.front.service;

import com.cqmike.front.config.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @program: iot
 * @ClassName: KafkaService
 * @Description: kafka服务类
 * @Author: chen qi
 * @Date: 2020/3/6 22:13
 * @Version: 1.0
 **/
@Slf4j
@Component
public class KafkaService {

    @Resource
    private KafkaTemplate<String, Message> kafkaTemplate;


    /**
     *  同步发送
     * @param topic
     * @param data
     */
    public void sendDataToKafkaTopic(String topic, Object data) {

        Message message = new Message(data);
        log.info("kafka发送data到({}), 数据为: ({})", topic, message);
        kafkaTemplate.send(topic, message);
    }

    /**
     *  异步发送
     * @param topic
     * @param data
     */
    public void asyncSendDataToKafkaTopic(String topic, Object data) {

        Message message = new Message(data);
        log.info("kafka发送data到({}), 数据为: ({})", topic, message);
        ListenableFuture<SendResult<String, Message>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("kafka发送消息失败, ex = ({}), topic = ({}) , data = ({})", throwable, topic, message);
            }

            @Override
            public void onSuccess(SendResult<String, Message> stringStringSendResult) {
                log.debug("kafka发送消息成功, topic = ({}), data = ({})", topic, message);
            }
        });
    }
}
