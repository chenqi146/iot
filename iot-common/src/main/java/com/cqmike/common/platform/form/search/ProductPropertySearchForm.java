package com.cqmike.common.platform.form.search;

import com.cqmike.core.form.BaseSearchForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @program: 
 * @Interface: ProductPropertySearchForm
 * @Description: ProductPropertySearchForm搜索表单类
 * @Author: chen qi
 * @Date: 2020-21-02 21:33
 * @Version: 1.0
**/
@ApiModel(value = "ProductPropertySearchForm")
public class ProductPropertySearchForm extends BaseSearchForm {

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 创建用户
     */
    @ApiModelProperty("创建用户")
    private String createUserName;

    /**
     * 最后修改时间
     */
    @ApiModelProperty("最后修改时间")
    private Date updateTime;

    /**
     * 最后修改用户
     */
    @ApiModelProperty("最后修改用户")
    private String updateUserName;

    /**
     * 属性描述
     */
    @ApiModelProperty("属性描述")
    private String description;

    /**
     * 属性标识符
     */
    @ApiModelProperty("属性标识符")
    private String identifier;

    /**
     * 最大值
     */
    @ApiModelProperty("最大值")
    private Double max;

    /**
     * 最小值
     */
    @ApiModelProperty("最小值")
    private Double min;

    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    private String name;

    /**
     * 产品id
     */
    @ApiModelProperty("产品id")
    private String productId;

    /**
     * 属性类型
     */
    @ApiModelProperty("属性类型")
    private String type;

    /**
     * 属性值
     */
    @ApiModelProperty("属性值")
    private String value;

    /**
     * 属性单位
     */
    @ApiModelProperty("属性单位")
    private String unit;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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