package com.cqmike.common.platform.form.search;

import com.cqmike.core.form.BaseSearchForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: 
 * @Interface: DeviceRecordSearchForm
 * @Description: DeviceRecordSearchForm搜索表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:20
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DeviceRecordSearchForm")
@EqualsAndHashCode(callSuper = true)
public class DeviceRecordSearchForm extends BaseSearchForm {

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

    /**
     * 创建用户
     */
    @ApiModelProperty("创建用户")
    private String createUserName;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 修改用户
     */
    @ApiModelProperty("修改用户")
    private String updateUserName;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;


}