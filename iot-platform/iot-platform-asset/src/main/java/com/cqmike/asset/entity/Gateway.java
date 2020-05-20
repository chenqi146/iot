package com.cqmike.asset.entity;

import com.cqmike.core.entity.BaseEntity;
import com.cqmike.user.interceptor.CustomerListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * @program: 
 * @Interface: GatewayEntity
 * @Description: GatewayEntity实体
 * @Author: chen qi
 * @Date: 2020-16-07 16:22
 * @Version: 1.0
**/
@Entity
@Table(name = "gateway")
@ApiModel(value = "网关实体")
@EntityListeners(value = {CustomerListener.class})
public class Gateway extends BaseEntity {


    /**
     * 网关设备id
     */
    @Column(
            name = "device_id",
            columnDefinition = "varchar(32) not null comment '网关设备id'"
    )
    @ApiModelProperty("网关设备id")
    private String deviceId;


    /**
     * 网关子设备id
     */
    @Column(
            name = "child_device_id",
            columnDefinition = "varchar(32) not null comment '网关子设备id'"
    )
    @ApiModelProperty("网关子设备id")
    private String childDeviceId;
    /**
     * 网关设备id
     */
    @Column(
            name = "device_sn",
            columnDefinition = "varchar(32) not null comment '网关设备sn'"
    )
    @ApiModelProperty("网关设备sn")
    private String deviceSn;


    /**
     * 网关子设备id
     */
    @Column(
            name = "child_device_Sn",
            columnDefinition = "varchar(32) not null comment '网关子设备sn'"
    )
    @ApiModelProperty("网关子设备sn")
    private String childDeviceSn;

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
}