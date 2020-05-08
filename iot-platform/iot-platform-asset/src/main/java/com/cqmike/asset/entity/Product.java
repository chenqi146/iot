package com.cqmike.asset.entity;

import com.cqmike.common.platform.enums.AccessTypeEnum;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.core.entity.BaseEntity;
import com.cqmike.user.interceptor.CustomerListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @program: iot
 * @ClassName: Product
 * @Description: 产品类
 * @Author: chen qi
 * @Date: 2020/2/29 19:12
 * @Version: 1.0
 **/
@Entity
@Table(name = "product")
@ApiModel(value = "产品实体")
@EntityListeners(value = {CustomerListener.class})
public class Product extends BaseEntity {

    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    @Column(
            name = "name",
            columnDefinition = "varchar(64) DEFAULT NULL COMMENT '产品名称'"
    )
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
     * 产品厂商
     */
    @ApiModelProperty(value = "产品厂商")
    @Column(
            name = "vendor",
            columnDefinition = "varchar(32) NOT NULL COMMENT '产品厂商'"
    )
    private String vendor;

    /**
     * 产品型号
     */
    @ApiModelProperty(value = "产品型号")
    @Column(
            name = "model",
            columnDefinition = "varchar(32) NOT NULL COMMENT '产品型号'"
    )
    private String model;

    /**
     * 接入方式
     */
    @ApiModelProperty(value = "接入方式")
    @Column(
            name = "access_type",
            columnDefinition = "varchar(16) NOT NULL COMMENT '接入方式'"
    )
    @Enumerated(EnumType.STRING)
    private AccessTypeEnum accessType;

    /**
     * 产品描述
     */
    @ApiModelProperty(value = "产品描述")
    @Column(
            name = "description",
            columnDefinition = "varchar(256) DEFAULT NULL COMMENT '产品描述'"
    )
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
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

    public AccessTypeEnum getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessTypeEnum accessType) {
        this.accessType = accessType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
