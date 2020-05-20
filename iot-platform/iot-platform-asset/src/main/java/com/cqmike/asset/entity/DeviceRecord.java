package com.cqmike.asset.entity;

import com.cqmike.core.entity.BaseEntity;
import com.cqmike.user.interceptor.CustomerListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.Date;

/**
 * @program: iot
 * @ClassName: DeviceRecord
 * @Description: 设备记录类
 * @Author: chen qi
 * @Date: 2020/2/29 19:12
 * @Version: 1.0
 **/
@Entity
@Table(name = "device_record")
@ApiModel(value = "设备数据实体")
@EntityListeners(value = {CustomerListener.class})
public class DeviceRecord extends BaseEntity {

    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备sn")
    @Column(
            name = "device_Sn",
            columnDefinition = "varchar(32) NOT NULL COMMENT '设备sn'"
    )
    private String deviceSn;

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
     * 设备数据
     */
    @ApiModelProperty(value = "设备数据")
    @Column(
            name = "data",
            columnDefinition = "json DEFAULT NULL COMMENT '设备数据'"
    )
    private String data;

    /**
     * 数据接收时间
     */
    @ApiModelProperty(value = "数据接收时间")
    @Column(
            name = "receive_time",
            columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据接收时间'"
    )
    private Date receiveTime;

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceId) {
        this.deviceSn = deviceId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}
