package com.cqmike.common.platform.form;

import com.cqmike.common.platform.enums.MiddleTypeEnum;
import com.cqmike.common.platform.enums.RuleStatusEnum;
import com.cqmike.common.platform.enums.RuleTypeEnum;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: 
 * @Interface: RuleForm
 * @Description: RuleForm表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:24
 * @Version: 1.0
**/
@ApiModel(value = "RuleForm")
public class RuleForm extends BaseForm {
    /**
     * 产品id
     */
    @ApiModelProperty("产品id")
    private String productId;

    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    private String productName;

    /**
     * 中间件id
     */
    @ApiModelProperty("中间件类型")
    private MiddleTypeEnum middlewareType;

    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    private String name;

    /**
     * 规则类型   流转
     */
    @ApiModelProperty("规则类型   流转")
    private RuleTypeEnum type;

    /**
     * 规则类型   流转
     */
    @ApiModelProperty("规则类型名称   流转")
    private String typeName;

    /**
     * 规则描述
     */
    @ApiModelProperty("规则描述")
    private String description;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private RuleStatusEnum status;

    /**
     * 状态名称
     */
    @ApiModelProperty("状态名称")
    private String statusName;

    /**
     * TOPIC
     */
    @ApiModelProperty("TOPIC")
    private String topic;

    public RuleForm() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public MiddleTypeEnum getMiddlewareType() {
        return middlewareType;
    }

    public void setMiddlewareType(MiddleTypeEnum middlewareType) {
        this.middlewareType = middlewareType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RuleTypeEnum getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RuleStatusEnum getStatus() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setType(RuleTypeEnum type) {
        this.type = type;
        this.typeName = type.getMsg();
    }

    public void setStatus(RuleStatusEnum status) {
        this.status = status;
        this.statusName = status.getMsg();
    }
}