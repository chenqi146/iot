package com.cqmike.front.netty.decoder;

import com.cqmike.common.dto.AnalyseDataDTO;
import com.cqmike.common.dto.Message;
import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.common.platform.enums.MiddleTypeEnum;
import com.cqmike.common.platform.enums.RuleTypeEnum;
import com.cqmike.core.util.JsonUtils;
import com.cqmike.front.map.RuleFormMap;
import com.cqmike.front.service.KafkaService;
import com.cqmike.front.service.MqttSender;
import com.cqmike.front.util.SpringContextUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @program: iot
 * @ClassName: DistributionDecoder
 * @Description: 消息分发处理类
 * @Author: chen qi
 * @Date: 2020/3/7 10:54
 * @Version: 1.0
 **/
public class DistributionDecoder extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(DistributionDecoder.class);

    private KafkaService kafkaService = SpringContextUtil.getBean(KafkaService.class);
    private MqttSender mqttSender = SpringContextUtil.getBean(MqttSender.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        AnalyseDataDTO dto = (AnalyseDataDTO) msg;

        String productId = dto.getProductId();
        List<RuleFormForFront> ruleFormList = RuleFormMap.get(productId);

        kafkaService.asyncSendDataToKafkaTopic("deviceRecordData", dto);
        log.debug("数据推送到kafka——topic: deviceRecordData, 数据为: ({})", dto);

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
                try {
                    Message message = new Message(dto);
                    String json = JsonUtils.toJson(message);
                    mqttSender.sendToMqtt(topic, json);
                    log.info("数据推送到mqtt——topic: ({}), 数据为: ({})", topic, json);
                } catch (Exception e) {
                    log.error("mqtt发送消息异常, 设备sn为[{}]", dto.getDeviceSn(), e);
                }

            }
        }

    }
}
