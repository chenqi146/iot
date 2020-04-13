package com.cqmike.common.platform.form;
import com.cqmike.core.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: 
 * @Interface: UserForm
 * @Description: UserForm表单类
 * @Author: chen qi
 * @Date: 2020-17-22 17:58
 * @Version: 1.0
**/
@ApiModel(value = "UserForm")
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

    public UserForm() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}