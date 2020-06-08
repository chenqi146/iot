package com.cqmike.asset.entity;

import com.cqmike.common.platform.enums.DeviceStatusEnum;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.core.entity.BaseEntity;
import com.cqmike.user.interceptor.CustomerListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: 
 * @Interface: DeviceEntity
 * @Description: DeviceEntity实体
 * @Author: chen qi
 * @Date: 2020-19-24 19:42
 * @Version: 1.0
**/
@Entity
@Table(name = "device")
@ApiModel(value = "设备实体")
@EntityListeners(value = {CustomerListener.class})
public class Device extends BaseEntity {


    /**
     * 安装日期
     */
    @Column(
            name = "installation_date",
            columnDefinition = "timestamp null comment '安装日期'"
    )
    @ApiModelProperty("安装日期")
    private Date installationDate;


    /**
     * 安装地址
     */
    @Column(
            name = "installation_location",
            columnDefinition = "varchar(32) null comment '安装地址'"
    )
    @ApiModelProperty("安装地址")
    private String installationLocation;


    /**
     * 设备名称
     */
    @Column(
            name = "name",
            columnDefinition = "varchar(64) null comment '设备名称'"
    )
    @ApiModelProperty("设备名称")
    private String name;

    /**
     * 产品类型 GATAWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备
     */
    @ApiModelProperty(value = "产品类型 GATAWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备")
    @Column(
            name = "type",
            columnDefinition = "varchar(16) NOT NULL COMMENT '产品类型 GATAWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备'"
    )
    @Enumerated(EnumType.STRING)
    private ProductTypeEnum type;

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
     * 产品id
     */
    @Column(
            name = "product_name",
            columnDefinition = "varchar(32) not null comment '产品名称'"
    )
    @ApiModelProperty("产品id")
    private String productName;


    /**
     * 设备sn
     */
    @Column(
            name = "sn",
            columnDefinition = "varchar(64) not null comment '设备sn'"
    )
    @ApiModelProperty("设备sn")
    private String sn;


    /**
     * 状态  ONLINE-在线  离线
     */
    @Column(
            name = "status",
            columnDefinition = "varchar(16) not null comment '状态  ONLINE-在线  离线'"
    )
    @ApiModelProperty("状态  ONLINE-在线  离线")
    @Enumerated(EnumType.STRING)
    private DeviceStatusEnum status;


    /**
     * 设备最后上线时间
     */
    @Column(
            name = "last_online_time",
            columnDefinition = "timestamp null comment '设备最后上线时间'"
    )
    @ApiModelProperty("设备最后上线时间")
    private Date lastOnlineTime;

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
        this.status = status;
    }

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
    }
}