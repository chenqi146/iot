package com.cqmike.common.platform.form.search;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.cqmike.core.form.BaseSearchForm;

/**
 * @program: 
 * @Interface: DeviceLogSearchForm
 * @Description: DeviceLogSearchForm搜索表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:17
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DeviceLogSearchForm")
@EqualsAndHashCode(callSuper = true)
public class DeviceLogSearchForm extends BaseSearchForm {

    /**
     * 设备id
     */
    @ApiModelProperty("设备id")
    private String deviceId;

    /**
     * 操作类型  增删改
     */
    @ApiModelProperty("操作类型  增删改")
    private String type;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

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