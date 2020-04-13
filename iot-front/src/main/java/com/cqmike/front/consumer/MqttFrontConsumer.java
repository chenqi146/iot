package com.cqmike.front.consumer;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.cqmike.common.dto.AnalyseDataDTO;
import com.cqmike.common.dto.Message;
import com.cqmike.common.front.form.DeviceFormForFront;
import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.common.platform.enums.MiddleTypeEnum;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.common.platform.enums.RuleTypeEnum;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.core.result.ReturnForm;
import com.cqmike.core.util.JsonUtils;
import com.cqmike.front.client.PlatformClient;
import com.cqmike.front.map.PropertyFormMap;
import com.cqmike.front.map.RuleFormMap;
import com.cqmike.front.netty.decoder.AnalyseDecoder;
import com.cqmike.front.service.KafkaService;
import com.cqmike.front.service.MqttSender;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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

    @Resource
    private PlatformClient platformClient;

    @Resource
    private KafkaService kafkaService;

    @Resource
    private MqttSender mqttSender;

    /**
     *  获取topic的key
     **/
    private final static String TOPIC_KEY = "mqttReceivedTopic";

    @Bean
    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public MessageHandler handler() {
        return message -> {
            String receiveTopic = Objects.requireNonNull(message.getHeaders().get(TOPIC_KEY)).toString();
            String payload = String.valueOf(message.getPayload());
            log.info("receiveTopic: ({}), data: ({})", receiveTopic, payload);

            Message msg = JsonUtils.parse(payload, Message.class);
            Map<String, Object> map = JsonUtils.parse(msg.getMsg(), new TypeReference<Map<String, Object>>(){});
            String sn = MapUtil.getStr(map, "sn");
            ReturnForm<DeviceFormForFront> deviceForFront = platformClient.findDeviceForFrontBySn(sn);
            if (!deviceForFront.isSuccess()) {
                return;
            }

            DeviceFormForFront front = deviceForFront.getMessage();
            if (front == null) {
                log.error("deviceSn(({}))没有对应的设备信息", sn);
                return;
            }

            ProductForm currentProductForm = front.getCurrentProductForm();

            String productId;
            ProductTypeEnum productType = currentProductForm.getType();

            if (productType == ProductTypeEnum.GATEWAY) {

                Map<String, DeviceForm> childDeviceFormMap = front.getChildDeviceFormMap();
                if (CollectionUtils.isEmpty(childDeviceFormMap)) {
                    return;
                }
                DeviceForm form = childDeviceFormMap.get(sn);
                if (form == null) {
                    log.debug("deviceSn为 ({})的设备没有对应的设备信息", sn);
                    return;
                }
                productId = form.getProductId();

            } else {
                productId = currentProductForm.getId();
            }

            List<ProductPropertyForm> propertyFormList = PropertyFormMap.get(productId);
            if (CollectionUtil.isEmpty(propertyFormList)) {
                return;
            }

            // 校验数据
            Map<String, Object> resultMap = AnalyseDecoder.getResultMapForVerify(sn, map, propertyFormList);
            if (CollectionUtil.isEmpty(resultMap)) {
                return;
            }

            List<RuleFormForFront> ruleFormList = RuleFormMap.get(productId);

            AnalyseDataDTO dto = new AnalyseDataDTO(sn, productId, resultMap);

            kafkaService.asyncSendDataToKafkaTopic("deviceRecordData", dto);

            // 分发数据
            for (RuleFormForFront ruleForm : ruleFormList) {

                RuleTypeEnum ruleType = ruleForm.getRuleType();
                if (ruleType != RuleTypeEnum.CIRCULATION) {
                    continue;
                }
                MiddleTypeEnum middleType = ruleForm.getMiddlewareType();
                String topic = ruleForm.getTopic();
                if (middleType == MiddleTypeEnum.KAFKA) {
                    kafkaService.asyncSendDataToKafkaTopic(topic, dto);

                } else if (middleType == MiddleTypeEnum.MQTT) {
                    mqttSender.sendData(topic, dto);

                }
            }

        };
    }
}
