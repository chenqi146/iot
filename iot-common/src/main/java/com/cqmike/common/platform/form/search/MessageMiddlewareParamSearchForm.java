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
 * @Interface: MessageMiddlewareParamSearchForm
 * @Description: MessageMiddlewareParamSearchForm搜索表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:22
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MessageMiddlewareParamSearchForm")
@EqualsAndHashCode(callSuper = true)
public class MessageMiddlewareParamSearchForm extends BaseSearchForm {

    /**
     * 参数名称
     */
    @ApiModelProperty("参数名称")
    private String name;

    /**
     * 参数类型
     */
    @ApiModelProperty("参数类型")
    private String type;

    /**
     * 参数值
     */
    @ApiModelProperty("参数值")
    private String value;

    /**
     * 参数描述
     */
    @ApiModelProperty("参数描述")
    private String description;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

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