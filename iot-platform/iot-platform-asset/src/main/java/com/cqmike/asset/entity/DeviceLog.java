package com.cqmike.asset.entity;

import com.cqmike.common.platform.enums.DeviceOperateTypeEnum;
import com.cqmike.core.entity.BaseEntity;
import com.cqmike.user.interceptor.CustomerListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @program: iot
 * @ClassName: DeviceLog
 * @Description: 设备日志类
 * @Author: chen qi
 * @Date: 2020/2/29 19:12
 * @Version: 1.0
 **/
@Entity
@Table(name = "device_log")
@ApiModel(value = "设备日志实体")
@EntityListeners(value = {CustomerListener.class})
public class DeviceLog extends BaseEntity {

    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id")
    @Column(
            name = "device_id",
            columnDefinition = "varchar(32) NOT NULL COMMENT '设备id'"
    )
    private String deviceId;

    /**
     * 操作类型  增删改
     */
    @ApiModelProperty(value = "操作类型  增删改")
    @Column(
            name = "type",
            columnDefinition = "varchar(32) NOT NULL COMMENT '操作类型  增删改'"
    )
    @Enumerated(EnumType.STRING)
    private DeviceOperateTypeEnum type;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Column(
            name = "description",
            columnDefinition = "varchar(256) DEFAULT NULL COMMENT '描述'"
    )
    private String description;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public DeviceOperateTypeEnum getType() {
        return type;
    }

    public void setType(DeviceOperateTypeEnum type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
