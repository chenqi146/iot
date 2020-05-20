package com.cqmike.common.platform.form;

import com.cqmike.common.platform.enums.DataTypeEnum;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: 
 * @Interface: ProductPropertyForm
 * @Description: ProductPropertyForm表单类
 * @Author: chen qi
 * @Date: 2020-21-02 21:33
 * @Version: 1.0
**/
@ApiModel(value = "ProductPropertyForm")
public class ProductPropertyForm extends BaseForm {
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
    private DataTypeEnum type;

    /**
     * 属性类型名称
     */
    @ApiModelProperty("属性类型名称")
    private String typeName;

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

    public ProductPropertyForm() {
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

    public DataTypeEnum getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public void setType(DataTypeEnum type) {
        this.type = type;
        this.typeName = type.getMsg();
    }

    @Override
    public String toString() {
        return "ProductPropertyForm{" +
                "description='" + description + '\'' +
                ", identifier='" + identifier + '\'' +
                ", max=" + max +
                ", min=" + min +
                ", name='" + name + '\'' +
                ", productId='" + productId + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}