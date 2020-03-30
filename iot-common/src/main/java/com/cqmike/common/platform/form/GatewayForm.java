package com.cqmike.common.platform.form;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: 
 * @Interface: GatewayForm
 * @Description: GatewayForm表单类
 * @Author: chen qi
 * @Date: 2020-16-07 16:22
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GatewayForm")
@EqualsAndHashCode(callSuper = true)
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


}