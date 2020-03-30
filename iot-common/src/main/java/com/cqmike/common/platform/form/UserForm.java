package com.cqmike.common.platform.form;
import com.cqmike.core.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: 
 * @Interface: UserForm
 * @Description: UserForm表单类
 * @Author: chen qi
 * @Date: 2020-17-22 17:58
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserForm")
@EqualsAndHashCode(callSuper = true)
public class UserForm extends BaseForm {
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String userName;

    /**
     * 登录名
     */
    @ApiModelProperty("登录名")
    private String loginName;

    /**
     * 密码
     */
    @JsonIgnore
    @ApiModelProperty("密码")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;


}