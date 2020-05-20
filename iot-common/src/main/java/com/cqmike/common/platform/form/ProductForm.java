package com.cqmike.common.platform.form;

import com.cqmike.common.platform.enums.AccessTypeEnum;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: 
 * @Interface: ProductForm
 * @Description: ProductForm表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:22
 * @Version: 1.0
**/
@ApiModel(value = "ProductForm")
public class ProductForm extends BaseForm {

    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    private String name;

    /**
     * 产品类型 GATEWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备
     */
    @ApiModelProperty("产品类型 GATEWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备")
    private ProductTypeEnum type;

    /**
     * 产品类型 GATAWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备
     */
    @ApiModelProperty("产品类型 GATAWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备")
    private String typeName;

    /**
     * 产品厂商
     */
    @ApiModelProperty("产品厂商")
    private String vendor;

    /**
     * 产品型号
     */
    @ApiModelProperty("产品型号")
    private String model;

    /**
     * 接入方式
     */
    @ApiModelProperty("接入方式")
    private AccessTypeEnum accessType;

    /**
     * 接入方式
     */
    @ApiModelProperty("接入方式")
    private String accessTypeName;

    /**
     * 产品描述
     */
    @ApiModelProperty("产品描述")
    private String description;

    /**
     * 设备数量
     */
    @ApiModelProperty("设备数量")
    private Integer deviceNumber;

    /**
     * 产品周期
     */
    @ApiModelProperty("产品周期")
    private Integer cycle;

    public ProductForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductTypeEnum getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public AccessTypeEnum getAccessType() {
        return accessType;
    }

    public String getAccessTypeName() {
        return accessTypeName;
    }

    public void setAccessTypeName(String accessTypeName) {
        this.accessTypeName = accessTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(Integer deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
        this.typeName = type.getMsg();
    }

    public void setAccessType(AccessTypeEnum accessType) {
        this.accessType = accessType;
        this.accessTypeName = accessType.getMsg();
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }
}