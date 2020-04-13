package com.cqmike.front.consumer;

import cn.hutool.core.util.StrUtil;
import com.cqmike.common.constant.Constant;
import com.cqmike.common.dto.Message;
import com.cqmike.common.dto.RuleScriptDTO;
import com.cqmike.common.front.enums.OperateTypeEnum;
import com.cqmike.core.util.JsonUtils;
import com.cqmike.front.map.CompiledScriptMap;
import com.cqmike.front.map.RuleFormMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.script.ScriptException;

/**
 * @program: iot
 * @ClassName: KafkaFrontConsumer
 * @Description: kafka消费者   维护 rule和产品解析js
 * @Author: chen qi
 * @Date: 2020/3/19 20:22
 * @Version: 1.0
 **/
@Component
public class KafkaFrontConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaFrontConsumer.class);

    @KafkaListener(topics = {Constant.UPDATE_RULE, Constant.UPDATE_SCRIPT})
    public void handle(ConsumerRecord<String, Message> record) throws ScriptException {
        log.info("methodName: handle ----- params: record = [{}]", record);
        if (record == null) {
            return;
        }
        String topic = record.topic();
        Message value = record.value();
        String msg = value.getMsg();
        if (StrUtil.isEmpty(msg)) {
            return;
        }

        // 根据操作类型 动态维护 规则和解析脚本
        RuleScriptDTO dto = JsonUtils.parse(msg, RuleScriptDTO.class);
        OperateTypeEnum operateType = dto.getOperateType();
        if (Constant.UPDATE_RULE.equals(topic)) {
            RuleFormMap.update(dto.getRuleForm(), operateType);
        } else if (Constant.UPDATE_SCRIPT.equals(topic)) {
            CompiledScriptMap.update(dto.getParserForm(), operateType);
        }

    }


}
