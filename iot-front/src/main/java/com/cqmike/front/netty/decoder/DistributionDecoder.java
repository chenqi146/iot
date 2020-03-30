package com.cqmike.front.netty.decoder;

import com.cqmike.common.platform.enums.MiddleTypeEnum;
import com.cqmike.common.platform.enums.RuleTypeEnum;
import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.core.util.JsonUtils;
import com.cqmike.common.dto.AnalyseDataDTO;
import com.cqmike.front.map.RuleFormMap;
import com.cqmike.front.service.KafkaService;
import com.cqmike.front.service.MqttSender;
import com.cqmike.front.util.SpringContextUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @program: iot
 * @ClassName: DistributionDecoder
 * @Description: 消息分发处理类
 * @Author: chen qi
 * @Date: 2020/3/7 10:54
 * @Version: 1.0
 **/
@Slf4j
public class DistributionDecoder extends ChannelInboundHandlerAdapter {

    private KafkaService kafkaService = SpringContextUtil.getBean(KafkaService.class);
    private MqttSender mqttSender = SpringContextUtil.getBean(MqttSender.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        AnalyseDataDTO dto = (AnalyseDataDTO) msg;

        String productId = dto.getProductId();
        List<RuleFormForFront> ruleFormList = RuleFormMap.get(productId);

        String dataJson = JsonUtils.toJson(dto);

        kafkaService.asyncSendDataToKafkaTopic("deviceRecordData", dataJson);
        log.debug("数据推送到kafka——topic: deviceRecordData, 数据为: ({})", dataJson);

        // todo 哪个中间件
        for (RuleFormForFront ruleForm : ruleFormList) {
            RuleTypeEnum ruleType = ruleForm.getRuleType();
            if (ruleType == RuleTypeEnum.CIRCULATION) {

                MiddleTypeEnum middleType = ruleForm.getMiddleType();
                String topic = ruleForm.getTopic();
                if (middleType == MiddleTypeEnum.KAFKA) {
                    kafkaService.asyncSendDataToKafkaTopic(topic, dataJson);
                    log.debug("数据推送到kafka——topic: ({}), 数据为: ({})", topic, dataJson);

                } else if (middleType == MiddleTypeEnum.MQTT) {
                    mqttSender.sendToMqtt(topic, dataJson);
                    log.debug("数据推送到mqtt——topic: ({}), 数据为: ({})", topic, dataJson);

                }
            }

        }

    }
}
