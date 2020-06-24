package com.cqmike.common.dto;

import com.cqmike.common.front.enums.OperateTypeEnum;
import com.cqmike.common.front.form.ParserFormForFront;
import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.common.platform.form.ProductPropertyForm;

import java.io.Serializable;

/**
 * @program: iot
 * @ClassName: RuleScriptDTO
 * @Description: 规则和脚本、属性传输对象  平台层发送数据变化  传输此对象维护front的数据
 * @Author: chen qi
 * @Date: 2020/3/19 20:38
 * @Version: 1.0
 **/
public class RuleScriptDTO implements Serializable {

    private static final long serialVersionUID = -652369407000672600L;
    /**
     *  操作类型
     */
    private OperateTypeEnum operateType;
    /**
     *  规则
     */
    private RuleFormForFront ruleForm;
    private ParserFormForFront parserForm;
    private ProductPropertyForm propForm;
    private String productId;

    public RuleScriptDTO() {
    }

    public RuleScriptDTO(OperateTypeEnum operateType, RuleFormForFront ruleForm, ParserFormForFront parserForm, String productId) {
        this.operateType = operateType;
        this.ruleForm = ruleForm;
        this.parserForm = parserForm;
        this.productId = productId;
    }

    public OperateTypeEnum getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateTypeEnum operateType) {
        this.operateType = operateType;
    }

    public ProductPropertyForm getPropForm() {
        return propForm;
    }

    public void setPropForm(ProductPropertyForm propForm) {
        this.propForm = propForm;
    }

    public RuleFormForFront getRuleForm() {
        return ruleForm;
    }

    public void setRuleForm(RuleFormForFront ruleForm) {
        this.ruleForm = ruleForm;
    }

    public ParserFormForFront getParserForm() {
        return parserForm;
    }

    public void setParserForm(ParserFormForFront parserForm) {
        this.parserForm = parserForm;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "RuleScriptDTO{" +
                "operateType=" + operateType +
                ", ruleForm=" + ruleForm +
                ", parserForm=" + parserForm +
                ", propForm=" + propForm +
                ", productId='" + productId + '\'' +
                '}';
    }
}
