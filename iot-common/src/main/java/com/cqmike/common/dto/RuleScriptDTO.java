package com.cqmike.common.dto;

import com.cqmike.common.front.enums.OperateTypeEnum;
import com.cqmike.common.front.form.ParserFormForFront;
import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.common.platform.form.ProductPropertyForm;

/**
 * @program: iot
 * @ClassName: RuleScriptDTO
 * @Description: 规则和脚本传输对象
 * @Author: chen qi
 * @Date: 2020/3/19 20:38
 * @Version: 1.0
 **/
public class RuleScriptDTO {

    private OperateTypeEnum operateType;
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
