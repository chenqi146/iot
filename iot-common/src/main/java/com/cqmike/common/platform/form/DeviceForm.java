package com.cqmike.common.platform.form;

import com.cqmike.common.platform.enums.DeviceStatusEnum;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.core.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @program: 
 * @Interface: DeviceForm
 * @Description: DeviceForm表单类
 * @Author: chen qi
 * @Date: 2020-19-24 19:42
 * @Version: 1.0
**/
@ApiModel(value = "DeviceForm")
public class DeviceForm extends BaseForm {
    /**
     * 安装日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("安装日期")
    private Date installationDate;

    /**
     * 安装地址
     */
    @ApiModelProperty("安装地址")
    private String installationLocation;

    /**
     * 设备名称
     */
    @ApiModelProperty("设备名称")
    private String name;

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
     * 父产品名称
     */
    @ApiModelProperty("父产品名称")
    private String parentProductName;

    /**
     * 父设备名称
     */
    @ApiModelProperty("父设备名称")
    private String parentDeviceName;

    /**
     * 设备类型 GATEWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备
     */
    @ApiModelProperty("设备类型 GATEWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备")
    private ProductTypeEnum type;

    /**
     * 设备类型 GATEWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备
     */
    @ApiModelProperty("设备类型 GATEWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备")
    private String typeName;

    /**
     * 设备sn
     */
    @ApiModelProperty("设备sn")
    private String sn;

    /**
     * 状态  ONLINE-在线  离线
     */
    @ApiModelProperty("状态  ONLINE-在线  离线")
    private DeviceStatusEnum status;

    /**
     * 状态名称  ONLINE-在线  离线
     */
    @ApiModelProperty("状态名称  ONLINE-在线  离线")
    private String statusName;

    /**
     * 设备最后上线时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("设备最后上线时间")
    private Date lastOnlineTime;

    private String parentDeviceId;

    public DeviceForm() {
    }

    public String getParentDeviceId() {
        return parentDeviceId;
    }

    public void setParentDeviceId(String parentDeviceId) {
        this.parentDeviceId = parentDeviceId;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public String getInstallationLocation() {
        return installationLocation;
    }

    public void setInstallationLocation(String installationLocation) {
        this.installationLocation = installationLocation;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getParentProductName() {
        return parentProductName;
    }

    public void setParentProductName(String parentProductName) {
        this.parentProductName = parentProductName;
    }

    public String getParentDeviceName() {
        return parentDeviceName;
    }

    public void setParentDeviceName(String parentDeviceName) {
        this.parentDeviceName = parentDeviceName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public DeviceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeviceStatusEnum status) {
        if (status != null) {
            this.statusName = status.getMsg();
        }
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
        this.typeName = type.getMsg();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}