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
 * @Interface: RuleSearchForm
 * @Description: RuleSearchForm搜索表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:24
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RuleSearchForm")
@EqualsAndHashCode(callSuper = true)
public class RuleSearchForm extends BaseSearchForm {

    /**
     * 产品id
     */
    @ApiModelProperty("产品id")
    private String productId;

    /**
     * 中间件id
     */
    @ApiModelProperty("中间件id")
    private String middlewareId;

    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    private String name;

    /**
     * 规则类型   流转
     */
    @ApiModelProperty("规则类型   流转")
    private String type;

    /**
     * 规则描述
     */
    @ApiModelProperty("规则描述")
    private String description;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String status;

    /**
     * TOPIC
     */
    @ApiModelProperty("TOPIC")
    private String topic;

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