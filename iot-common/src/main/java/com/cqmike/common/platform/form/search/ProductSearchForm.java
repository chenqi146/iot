package com.cqmike.common.platform.form.search;

import com.cqmike.core.annotation.Query;
import com.cqmike.core.annotation.QueryType;
import com.cqmike.core.form.BaseSearchForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @program: 
 * @Interface: ProductSearchForm
 * @Description: ProductSearchForm搜索表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:22
 * @Version: 1.0
**/
@ApiModel(value = "ProductSearchForm")
public class ProductSearchForm extends BaseSearchForm {

    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    @Query(QueryType.like)
    private String name;

    /**
     * 产品类型 GATAWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备
     */
    @ApiModelProperty("产品类型 GATAWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备")
    private String type;

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
    private String accessType;

    /**
     * 产品描述
     */
    @ApiModelProperty("产品描述")
    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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