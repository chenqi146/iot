package com.cqmike.common.platform.form;

import com.cqmike.common.platform.enums.AccessTypeEnum;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: 
 * @Interface: ProductForm
 * @Description: ProductForm表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:22
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ProductForm")
@EqualsAndHashCode(callSuper = true)
public class ProductForm extends BaseForm {

    /**
     * 产品id
    **/
    @ApiModelProperty("产品id")
    private String id;

    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    private String name;

    /**
     * 产品类型 GATAWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备
     */
    @ApiModelProperty("产品类型 GATAWAY-网关，DEVICE-设备 CHILD_DEVICE-子设备")
    private ProductTypeEnum type;

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
     * 产品描述
     */
    @ApiModelProperty("产品描述")
    private String description;


}