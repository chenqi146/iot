package com.cqmike.front.netty.decoder;

import com.cqmike.asset.form.front.RuleFormForFront;
import com.cqmike.core.util.JsonUtils;
import com.cqmike.front.form.AnalyseDataDTO;
import com.cqmike.front.netty.Connection;
import com.cqmike.front.netty.Const;
import com.cqmike.front.service.KafkaService;
import com.cqmike.front.service.MqttSender;
import com.cqmike.front.util.SpringContextUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

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
        if (dto == null) {
            return;
        }

        Connection connection = ctx.channel().attr(Const.CONNECTION).get();
        Map<String, List<RuleFormForFront>> ruleFormMap = connection.getRuleFormMap();
        if (CollectionUtils.isEmpty(ruleFormMap)) {
            return;
        }

        String deviceSn = dto.getDeviceSn();
        List<RuleFormForFront> ruleFormList = ruleFormMap.get(deviceSn);
        if (CollectionUtils.isEmpty(ruleFormList)) {
            return;
        }

        String dataJson = JsonUtils.toJson(dto);

        kafkaService.asyncSendDataToKafkaTopic("deviceRecordData", dataJson);
        log.debug("数据推送到kafka——topic: deviceRecordData, 数据为: {}", dataJson);

        for (RuleFormForFront ruleForm : ruleFormList) {
            String ruleType = ruleForm.getRuleType();
            //todo 枚举

            String middleType = ruleForm.getMiddleType();
            String topic = ruleForm.getTopic();
            if (middleType.equals("")) {
                kafkaService.asyncSendDataToKafkaTopic(topic, dataJson);
                log.debug("数据推送到kafka——topic: {}, 数据为: {}", topic, dataJson);

            } else if (middleType.equals("mqtt")) {
                mqttSender.sendToMqtt(topic, dataJson);
                log.debug("数据推送到mqtt——topic: {}, 数据为: {}", topic, dataJson);

            }
        }

    }
}
