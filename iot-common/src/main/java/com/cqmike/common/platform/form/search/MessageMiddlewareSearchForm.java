package com.cqmike.common.platform.form.search;

import com.cqmike.core.annotation.Query;
import com.cqmike.core.annotation.QueryType;
import com.cqmike.core.form.BaseSearchForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @program: 
 * @Interface: MessageMiddlewareSearchForm
 * @Description: MessageMiddlewareSearchForm搜索表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:21
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MessageMiddlewareSearchForm")
@EqualsAndHashCode(callSuper = true)
public class MessageMiddlewareSearchForm extends BaseSearchForm {

    /**
     * id集合
     */
    @ApiModelProperty("id集合")
    @Query(value = QueryType.in, columnName = "id")
    private List<String> idList;
    /**
     * 实例名称
     */
    @ApiModelProperty("实例名称")
    private String name;

    /**
     * 实例类型
     */
    @ApiModelProperty("实例类型")
    private String type;

    /**
     * HOST
     */
    @ApiModelProperty("HOST")
    private String host;

    /**
     * 端口
     */
    @ApiModelProperty("端口")
    private String port;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

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