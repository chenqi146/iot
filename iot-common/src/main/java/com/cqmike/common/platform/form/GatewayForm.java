package com.cqmike.common.platform.form;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: 
 * @Interface: GatewayForm
 * @Description: GatewayForm表单类
 * @Author: chen qi
 * @Date: 2020-16-07 16:22
 * @Version: 1.0
**/
@ApiModel(value = "GatewayForm")
public class GatewayForm extends BaseForm {
    /**
     * 网关设备id
     */
    @ApiModelProperty("网关设备id")
    private String deviceId;

    /**
     * 网关子设备id
     */
    @ApiModelProperty("网关子设备id")
    private String childDeviceId;
    /**
     * 网关设备sn
     */
    @ApiModelProperty("网关设备sn")
    private String deviceSn;

    /**
     * 网关子设备sn
     */
    @ApiModelProperty("网关子设备sn")
    private String childDeviceSn;

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getChildDeviceSn() {
        return childDeviceSn;
    }

    public void setChildDeviceSn(String childDeviceSn) {
        this.childDeviceSn = childDeviceSn;
    }

    public GatewayForm() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChildDeviceId() {
        return childDeviceId;
    }

    public void setChildDeviceId(String childDeviceId) {
        this.childDeviceId = childDeviceId;
    }
}