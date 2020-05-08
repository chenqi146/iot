package com.cqmike.asset.consumer;

import com.cqmike.asset.service.DeviceService;
import com.cqmike.common.dto.Message;
import com.cqmike.common.dto.SocketMessage;
import com.cqmike.common.platform.enums.DeviceStatusEnum;
import com.cqmike.common.platform.form.DeviceForm;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: iot
 * @ClassName: KafkaConsumer
 * @Description: 平台端kafka
 * @Author: chen qi
 * @Date: 2020/3/21 18:06
 * @Version: 1.0
 **/
@Component
public class KafkaConsumer {

    private static final String DEVICE_OFFLINE = "deviceOffline";
    private static final String DEVICE_ONLINE = "deviceOnline";

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Resource
    private DeviceService deviceService;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     *  设备离线 上线 处理
     * @param record
     */
    @KafkaListener(topics = {DEVICE_OFFLINE, DEVICE_ONLINE})
    public void handleDevice(ConsumerRecord<String, Message> record) {
        log.info("methodName: handleDevice ----- params: record = [{}]", record);

        String topic = record.topic();
        Message value = record.value();

        DeviceForm deviceForm = deviceService.findOneBySn(value.getMsg());
        if (topic.equals(DEVICE_OFFLINE)) {
            deviceForm.setStatus(DeviceStatusEnum.OFFLINE);
        } else if (topic.equals(DEVICE_ONLINE)) {
            deviceForm.setStatus(DeviceStatusEnum.ONLINE);
            deviceForm.setLastOnlineTime(value.getDate());
        } else {
            return;
        }

        deviceService.update(deviceForm);
        SocketMessage socketMessage = new SocketMessage();
        Message message = new Message(socketMessage);
        simpMessagingTemplate.convertAndSend("/", message);

    }
}
