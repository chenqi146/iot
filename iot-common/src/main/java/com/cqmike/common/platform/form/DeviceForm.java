package com.cqmike.common.platform.form;

import com.cqmike.common.platform.enums.DeviceStatusEnum;
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
 * @Interface: DeviceForm
 * @Description: DeviceForm表单类
 * @Author: chen qi
 * @Date: 2020-19-24 19:42
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DeviceForm")
@EqualsAndHashCode(callSuper = true)
public class DeviceForm extends BaseForm {
    /**
     * 安装日期
     */
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
     * 设备最后上线时间
     */
    @ApiModelProperty("设备最后上线时间")
    private Date lastOnlineTime;


}