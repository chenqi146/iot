package com.cqmike.common.front.form;

import com.cqmike.common.platform.enums.MiddleTypeEnum;
import com.cqmike.common.platform.enums.RuleTypeEnum;
import com.cqmike.core.form.BaseForm;

/**
 * @program: iot
 * @ClassName: RuleFormForFront
 * @Description: 规则
 * @Author: chen qi
 * @Date: 2020/3/10 21:13
 * @Version: 1.0
 **/
public class RuleFormForFront extends BaseForm {

    private RuleTypeEnum ruleType;
    private String topic;
    private MiddleTypeEnum middlewareType;
    private String productId;

    public RuleTypeEnum getRuleType() {
        return ruleType;
    }

    public void setRuleType(RuleTypeEnum ruleType) {
        this.ruleType = ruleType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public MiddleTypeEnum getMiddlewareType() {
        return middlewareType;
    }

    public void setMiddlewareType(MiddleTypeEnum middlewareType) {
        this.middlewareType = middlewareType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
