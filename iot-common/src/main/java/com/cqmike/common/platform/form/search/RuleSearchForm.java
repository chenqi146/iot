package com.cqmike.common.platform.form.search;

import com.cqmike.common.platform.enums.MiddleTypeEnum;
import com.cqmike.common.platform.enums.RuleStatusEnum;
import com.cqmike.common.platform.enums.RuleTypeEnum;
import com.cqmike.core.annotation.Query;
import com.cqmike.core.annotation.QueryType;
import com.cqmike.core.form.BaseSearchForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @program: 
 * @Interface: RuleSearchForm
 * @Description: RuleSearchForm搜索表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:24
 * @Version: 1.0
**/
@ApiModel(value = "RuleSearchForm")
public class RuleSearchForm extends BaseSearchForm {

    /**
     * 产品id
     */
    @ApiModelProperty("产品id")
    private String productId;

    /**
     * 中间件id
     */
    @ApiModelProperty("中间件类型")
    private MiddleTypeEnum middlewareType;

    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    @Query(QueryType.like)
    private String name;

    /**
     * 规则类型   流转
     */
    @ApiModelProperty("规则类型   流转")
    private RuleTypeEnum type;

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
     * TOPIC
     */
    @ApiModelProperty("TOPIC")
    private String topic;

    /**
     * 创建用户
     */
    @ApiModelProperty("创建用户")
    private String createUserName;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 修改用户
     */
    @ApiModelProperty("修改用户")
    private String updateUserName;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public void setType(RuleTypeEnum type) {
        this.type = type;
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

    public void setStatus(RuleStatusEnum status) {
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}