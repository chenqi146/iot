package com.cqmike.common.platform.form;

import com.cqmike.common.platform.enums.MiddleTypeEnum;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: 
 * @Interface: MessageMiddlewareForm
 * @Description: MessageMiddlewareForm表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:21
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MessageMiddlewareForm")
@EqualsAndHashCode(callSuper = true)
public class MessageMiddlewareForm extends BaseForm {
    /**
     * 实例名称
     */
    @ApiModelProperty("实例名称")
    private String name;

    /**
     * 实例类型
     */
    @ApiModelProperty("实例类型")
    private MiddleTypeEnum type;

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


}