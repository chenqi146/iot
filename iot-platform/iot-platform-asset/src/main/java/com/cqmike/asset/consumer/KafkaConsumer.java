package com.cqmike.asset.consumer;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.cqmike.asset.service.DeviceRecordService;
import com.cqmike.asset.service.DeviceService;
import com.cqmike.asset.ws.WebSocketServer;
import com.cqmike.common.dto.AnalyseDataDTO;
import com.cqmike.common.dto.Message;
import com.cqmike.common.dto.SocketMessage;
import com.cqmike.common.platform.enums.DeviceStatusEnum;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.DeviceRecordForm;
import com.cqmike.core.util.JsonUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
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

    /**
     * 设备上线 topic
     */
    private static final String DEVICE_OFFLINE = "deviceOffline";

    /**
     * 设备离线 topic
     */
    private static final String DEVICE_ONLINE = "deviceOnline";

    /**
     * 设备数据topic
     */
    private static final String DEVICE_DATE = "deviceRecordData";

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Resource
    private DeviceService deviceService;

    @Resource
    private DeviceRecordService deviceRecordService;

    /**
     *  设备离线 上线 处理
     * @param record
     */
    @KafkaListener(topics = {DEVICE_OFFLINE, DEVICE_ONLINE})
    public void handleDevice(ConsumerRecord<String, Message> record) {
        log.info("methodName: handleDevice ----- params: record = [{}]", record);

        if (record == null) {
            return;
        }

        String topic = record.topic();
        Message value = record.value();
        if (value == null) {
            return;
        }

        String deviceSn = (String) value.getMsg();
        DeviceForm deviceForm = deviceService.findOneBySn(deviceSn);
        if (topic.equals(DEVICE_OFFLINE)) {
            deviceForm.setStatus(DeviceStatusEnum.OFFLINE);

            /**
             *  0-设备离线
             *  1-设备上线
             *  2-设备数据
             */
            SocketMessage socketMessage = new SocketMessage(0, deviceSn);
            WebSocketServer.sendMessage(deviceSn, socketMessage);
        } else if (topic.equals(DEVICE_ONLINE)) {
            deviceForm.setStatus(DeviceStatusEnum.ONLINE);
            deviceForm.setLastOnlineTime(value.getDate());
            SocketMessage socketMessage = new SocketMessage(1, deviceSn);
            WebSocketServer.sendMessage(deviceSn, socketMessage);
        } else {
            return;
        }

        // 更新设备状态和  上线时间
        deviceService.innerUpdate(deviceForm);
    }

    /**
     *  设备数据接收 推送给前端
     * @param record
     */
    @KafkaListener(topics = {DEVICE_DATE})
    public void handleDeviceData(ConsumerRecord<String, Message> record) {

        if (record == null) {
            return;
        }

        Message value = record.value();
        log.info("methodName: handleDeviceData ----- params: value = [{}]", value);

        if (value == null) {
            return;
        }

        AnalyseDataDTO dto = (AnalyseDataDTO) value.getMsg();
        String deviceSn = dto.getDeviceSn();
        if (StrUtil.isEmpty(deviceSn)) {
            return;
        }
        // 通知前端
        SocketMessage socketMessage = new SocketMessage(2, dto.getData());
        WebSocketServer.sendMessage(deviceSn, socketMessage);

        // 入库
        DeviceRecordForm recordForm = new DeviceRecordForm();
        recordForm.setReceiveTime(DateTime.now());
        recordForm.setProductId(dto.getProductId());
        recordForm.setDeviceSn(deviceSn);
        recordForm.setData(JsonUtils.toJson(dto.getData()));
        deviceRecordService.create(recordForm);
    }
}
