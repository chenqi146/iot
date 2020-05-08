package com.cqmike.asset.entity;

import com.cqmike.common.platform.enums.DataTypeEnum;
import com.cqmike.core.entity.BaseEntity;
import com.cqmike.user.interceptor.CustomerListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @program: 
 * @Interface: ProductPropertyEntity
 * @Description: ProductPropertyEntity实体
 * @Author: chen qi
 * @Date: 2020-21-02 21:35
 * @Version: 1.0
**/
@Entity
@Table(name = "product_property")
@ApiModel(value = "产品属性实体")
@EntityListeners(value = {CustomerListener.class})
public class ProductProperty extends BaseEntity {


    /**
     * 属性描述
     */
    @Column(
            name = "description",
            columnDefinition = "varchar(256) null comment '属性描述'"
    )
    @ApiModelProperty("属性描述")
    private String description;


    /**
     * 属性标识符
     */
    @Column(
            name = "identifier",
            columnDefinition = "varchar(32) not null comment '属性标识符'"
    )
    @ApiModelProperty("属性标识符")
    private String identifier;


    /**
     * 最大值
     */
    @Column(
            name = "max",
            columnDefinition = "decimal(20, 6) null comment '最大值'"
    )
    @ApiModelProperty("最大值")
    private Double max;


    /**
     * 最小值
     */
    @Column(
            name = "min",
            columnDefinition = "decimal(20, 6) null comment '最小值'"
    )
    @ApiModelProperty("最小值")
    private Double min;


    /**
     * 属性名称
     */
    @Column(
            name = "name",
            columnDefinition = "varchar(64) null comment '属性名称'"
    )
    @ApiModelProperty("属性名称")
    private String name;


    /**
     * 产品id
     */
    @Column(
            name = "product_id",
            columnDefinition = "varchar(32) not null comment '产品id'"
    )
    @ApiModelProperty("产品id")
    private String productId;


    /**
     * 属性类型
     */
    @Column(
            name = "type",
            columnDefinition = "varchar(16) not null comment '属性类型'"
    )
    @ApiModelProperty("属性类型")
    @Enumerated(EnumType.STRING)
    private DataTypeEnum type;


    /**
     * 属性值
     */
    @Column(
            name = "value",
            columnDefinition = "varchar(64) null comment '属性值'"
    )
    @ApiModelProperty("属性值")
    private String value;


    /**
     * 属性单位
     */
    @Column(
            name = "unit",
            columnDefinition = "varchar(16) null comment '属性单位'"
    )
    @ApiModelProperty("属性单位")
    private String unit;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public DataTypeEnum getType() {
        return type;
    }

    public void setType(DataTypeEnum type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}