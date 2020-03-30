package com.cqmike.common.platform.form;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: 
 * @Interface: DeviceRecordForm
 * @Description: DeviceRecordForm表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:20
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DeviceRecordForm")
@EqualsAndHashCode(callSuper = true)
public class DeviceRecordForm extends BaseForm {
    /**
     * 设备id
     */
    @ApiModelProperty("设备id")
    private String deviceId;

    /**
     * 产品id
     */
    @ApiModelProperty("产品id")
    private String productId;

    /**
     * 设备数据
     */
    @ApiModelProperty("设备数据")
    private Object data;

    /**
     * 数据接收时间
     */
    @ApiModelProperty("数据接收时间")
    private Date receiveTime;


}