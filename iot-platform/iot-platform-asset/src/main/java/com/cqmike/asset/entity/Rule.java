package com.cqmike.asset.entity;

import com.cqmike.common.platform.enums.MiddleTypeEnum;
import com.cqmike.common.platform.enums.RuleStatusEnum;
import com.cqmike.common.platform.enums.RuleTypeEnum;
import com.cqmike.core.entity.BaseEntity;
import com.cqmike.user.interceptor.CustomerListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @program: iot
 * @ClassName: Rule
 * @Description: 规则类
 * @Author: chen qi
 * @Date: 2020/2/29 19:12
 * @Version: 1.0
 **/
@Entity
@Table(name = "rule")
@ApiModel(value = "规则实体")
@EntityListeners(value = {CustomerListener.class})
public class Rule extends BaseEntity {

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id")
    @Column(
            name = "product_id",
            columnDefinition = "varchar(32) NOT NULL COMMENT '产品id'"
    )
    private String productId;

    /**
     * 中间件id
     */
    @ApiModelProperty(value = "中间件类型")
    @Column(
            name = "middleware_type",
            columnDefinition = "varchar(32) NOT NULL COMMENT '中间件类型'"
    )
    private MiddleTypeEnum middlewareType;

    /**
     * 规则名称
     */
    @ApiModelProperty(value = "规则名称")
    @Column(
            name = "name",
            columnDefinition = "varchar(64) DEFAULT NULL COMMENT '规则名称'"
    )
    private String name;

    /**
     * 规则类型   流转
     */
    @ApiModelProperty(value = "规则类型   流转")
    @Column(
            name = "type",
            columnDefinition = "varchar(16) NOT NULL COMMENT '规则类型   流转'"
    )
    @Enumerated(EnumType.STRING)
    private RuleTypeEnum type;

    /**
     * 规则描述
     */
    @ApiModelProperty(value = "规则描述")
    @Column(
            name = "description",
            columnDefinition = "varchar(256) DEFAULT NULL COMMENT '规则描述'"
    )
    private String description;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @Column(
            name = "status",
            columnDefinition = "varchar(16) DEFAULT NULL COMMENT '状态'"
    )
    @Enumerated(EnumType.STRING)
    private RuleStatusEnum status;

    /**
     * TOPIC
     */
    @ApiModelProperty(value = "TOPIC")
    @Column(
            name = "topic",
            columnDefinition = "varchar(32) DEFAULT NULL COMMENT 'TOPIC'"
    )
    private String topic;

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
}
