package com.cqmike.front.consumer;

import cn.hutool.core.util.StrUtil;
import com.cqmike.asset.form.ProductPropertyParserForm;
import com.cqmike.asset.form.front.RuleFormForFront;
import com.cqmike.asset.service.ProductPropertyParserService;
import com.cqmike.asset.service.RuleService;
import com.cqmike.core.util.JsonUtils;
import com.cqmike.front.config.Message;
import com.cqmike.front.emnus.OperateTypeEnum;
import com.cqmike.front.form.RuleScriptDTO;
import com.cqmike.front.map.CompiledScriptMap;
import com.cqmike.front.map.RuleFormMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.script.ScriptException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: iot
 * @ClassName: KafkaFrontConsumer
 * @Description: kafka消费者   维护 rule和产品解析js
 * @Author: chen qi
 * @Date: 2020/3/19 20:22
 * @Version: 1.0
 **/
@Component
public class KafkaFrontConsumer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(KafkaFrontConsumer.class);

    private final static String UPDATE_RULE = "frontUpdateRule";
    private final static String UPDATE_SCRIPT = "frontUpdateScript";

    @Resource
    private RuleService ruleService;

    @Resource
    private ProductPropertyParserService parserService;

    /**
     *  启动的时候初始化 规则和脚本
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, Map<String, RuleFormForFront>> ruleFrontMap = ruleService.findRuleFrontList();
        RuleFormMap.init(ruleFrontMap);
        List<ProductPropertyParserForm> parserForms = parserService.listAll();
        Map<String, String> scriptMap = parserForms.stream().collect(Collectors
                .toMap(ProductPropertyParserForm::getProductId, ProductPropertyParserForm::getScript));
        CompiledScriptMap.init(scriptMap);
    }

    @KafkaListener(topics = {UPDATE_RULE, UPDATE_SCRIPT})
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
        if (UPDATE_RULE.equals(topic)) {
            RuleFormMap.update(dto.getRuleForm(), operateType);
        } else if (UPDATE_SCRIPT.equals(topic)) {
            CompiledScriptMap.update(dto.getParserForm(), operateType);
        }

    }


}
