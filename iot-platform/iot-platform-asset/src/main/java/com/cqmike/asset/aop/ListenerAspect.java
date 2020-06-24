package com.cqmike.asset.aop;

import cn.hutool.core.bean.BeanUtil;
import com.cqmike.asset.producer.KafkaService;
import com.cqmike.asset.service.impl.ProductPropertyParserServiceImpl;
import com.cqmike.asset.service.impl.ProductPropertyServiceImpl;
import com.cqmike.asset.service.impl.RuleServiceImpl;
import com.cqmike.common.constant.Constant;
import com.cqmike.common.dto.RuleScriptDTO;
import com.cqmike.common.front.enums.OperateTypeEnum;
import com.cqmike.common.front.form.ParserFormForFront;
import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.common.platform.form.ProductPropertyParserForm;
import com.cqmike.common.platform.form.RuleForm;
import com.cqmike.core.util.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: iot
 * @ClassName: ListenerAspect
 * @Description: 监听 产品的规则、属性、解析脚本的 CURD
 * 还有些方法没有监听到，因为参数不太好切，可以在对应的方法里面手动实现
 * @Author: chen qi
 * @Date: 2020/3/21 11:59
 * @Version: 1.0
 **/
@Aspect
@Component
public class ListenerAspect {

    private static final Logger log = LoggerFactory.getLogger(ListenerAspect.class);

    @Resource
    private KafkaService kafkaService;

    /**
     * 切入点  修改和创建方法  目标类为：  规则、属性、解析脚本
     */
    @Pointcut(
            "(execution(* com.cqmike.core.service.AbstractCurdService.update(..)) || " +
                    "execution(* com.cqmike.core.service.AbstractCurdService.create(..))) && ( " +
                    "target(com.cqmike.asset.service.impl.RuleServiceImpl) ||" +
                    "target(com.cqmike.asset.service.impl.ProductPropertyServiceImpl) ||" +
                    " target(com.cqmike.asset.service.impl.ProductPropertyParserServiceImpl))"
    )
    public void listenerSingle() {
    }

    @AfterReturning(value = "listenerSingle()", returning = "result")
    public void afterSingle(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        // 构建传输对象，参数和操作类型
        RuleScriptDTO build = getRuleScriptDTO(result, OperateTypeEnum.UPDATE);

        log.info("({})中的({})方法触发, 发送的消息为({})", className, methodName, build);
    }

    /**
     * 切入点  批量修改和创建方法  目标类为：  规则、属性、解析脚本
     */
    @Pointcut(
            "(execution(* com.cqmike.core.service.AbstractCurdService.updateInBatch(..)) || " +
                    "execution(* com.cqmike.core.service.AbstractCurdService.createInBatch(..))) && (" +
                    "target(com.cqmike.asset.service.impl.RuleServiceImpl) ||" +
                    "target(com.cqmike.asset.service.impl.ProductPropertyServiceImpl) ||" +
                    " target(com.cqmike.asset.service.impl.ProductPropertyParserServiceImpl))"
    )
    public void listenerBatch() {
    }

    @AfterReturning(value = "listenerBatch()", returning = "resultList")
    public void afterBatch(JoinPoint joinPoint, Object resultList) {
        Signature signature = joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        if (!(resultList instanceof List<?>)) {
            return;
        }

        List<?> list = (List<?>) resultList;

        List<RuleScriptDTO> dtoList = new ArrayList<>();
        for (Object o : list) {
            RuleScriptDTO dto = getRuleScriptDTO(o, OperateTypeEnum.UPDATE);
            dtoList.add(dto);
        }
        log.info("({})中的({})方法触发, 发送的消息为({})", className, methodName, JsonUtils.toJson(dtoList));
    }

    /**
     * 拼凑 消息对象 发送
     *
     * @param result 方法返回值
     * @return
     */
    private RuleScriptDTO getRuleScriptDTO(Object result, OperateTypeEnum typeEnum) {
        RuleScriptDTO build = new RuleScriptDTO();
        build.setOperateType(typeEnum);

        // 规则
        if (result instanceof RuleForm) {
            RuleForm ruleForm = (RuleForm) result;
            RuleFormForFront front = new RuleFormForFront();
            BeanUtil.copyProperties(ruleForm, front);
            front.setRuleType(ruleForm.getType());
            build.setProductId(front.getProductId());
            build.setRuleForm(front);
            kafkaService.asyncSendDataToKafkaTopic(Constant.UPDATE_RULE, build);

            // 解析脚本
        } else if (result instanceof ProductPropertyParserForm) {
            ProductPropertyParserForm parserForm = (ProductPropertyParserForm) result;
            ParserFormForFront front = new ParserFormForFront();
            BeanUtil.copyProperties(parserForm, front);
            build.setParserForm(front);
            build.setProductId(front.getProductId());
            kafkaService.asyncSendDataToKafkaTopic(Constant.UPDATE_SCRIPT, build);

            // 属性
        } else if (result instanceof ProductPropertyForm) {
            ProductPropertyForm propForm = (ProductPropertyForm) result;
            build.setPropForm(propForm);
            build.setProductId(propForm.getProductId());
            kafkaService.asyncSendDataToKafkaTopic(Constant.UPDATE_PROP, build);
        }
        return build;
    }


    /**
     * 切入点  删除方法  目标类为：  规则、属性、解析脚本
     */
    @Pointcut(
            "execution(* com.cqmike.core.service.AbstractCurdService.removeById(..)) &&( " +
                    "target(com.cqmike.asset.service.impl.RuleServiceImpl) ||" +
                    "target(com.cqmike.asset.service.impl.ProductPropertyServiceImpl) ||" +
                    " target(com.cqmike.asset.service.impl.ProductPropertyParserServiceImpl))"
    )
    public void listenerRemove() {
    }

    @After(value = "listenerRemove()")
    public void afterRemove(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        Object result = joinPoint.getArgs()[0];

        RuleScriptDTO build = getRuleScriptDTO(result, OperateTypeEnum.DELETE);

        log.info("({})中的({})方法触发, 发送的消息为({})", className, methodName, build);
    }

    /**
     *  删除全部  暂未使用
     */
    @Pointcut(
            "execution(* com.cqmike.core.service.AbstractCurdService.removeAll(..)) &&( " +
                    "target(com.cqmike.asset.service.impl.RuleServiceImpl) ||" +
                    "target(com.cqmike.asset.service.impl.ProductPropertyServiceImpl) ||" +
                    " target(com.cqmike.asset.service.impl.ProductPropertyParserServiceImpl))"
    )
    public void listenerRemoveAll() {
    }

//    @After(value = "listenerRemoveAll()")
    public void afterRemoveAll(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        String methodName = signature.getName();

        RuleScriptDTO build = new RuleScriptDTO();
        build.setOperateType(OperateTypeEnum.DELETE_ALL);

        if (target instanceof RuleServiceImpl) {
            kafkaService.asyncSendDataToKafkaTopic(Constant.UPDATE_RULE, build);
        } else if (target instanceof ProductPropertyParserServiceImpl) {
            kafkaService.asyncSendDataToKafkaTopic(Constant.UPDATE_SCRIPT, build);
        } else if (target instanceof ProductPropertyServiceImpl) {
            kafkaService.asyncSendDataToKafkaTopic(Constant.UPDATE_SCRIPT, build);
        }

        log.info("({})中的({})方法触发, 发送的消息为({})", className, methodName, build);
    }
}
