package com.cqmike.user.entity;

import com.cqmike.core.entity.BaseEntity;
import com.cqmike.user.interceptor.CustomerListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * @program: 
 * @Interface: UserEntity
 * @Description: UserEntity实体
 * @Author: chen qi
 * @Date: 2020-17-22 17:58
 * @Version: 1.0
**/
@Entity
@Table(name = "user")
@ApiModel(value = "用户实体")
@EntityListeners(value = {CustomerListener.class})
public class User extends BaseEntity {


    /**
     * 姓名
     */
    @Column(
            name = "user_name",
            columnDefinition = "varchar(32) not null comment '姓名'"
    )
    @ApiModelProperty("姓名")
    private String userName;


    /**
     * 登录名
     */
    @Column(
            name = "login_name",
            columnDefinition = "varchar(32) not null comment '登录名'"
    )
    @ApiModelProperty("登录名")
    private String loginName;


    /**
     * 密码
     */
    @Column(
            name = "password",
            columnDefinition = "varchar(32) not null comment '密码'"
    )
    @ApiModelProperty("密码")
    private String password;


    /**
     * 邮箱
     */
    @Column(
            name = "email",
            columnDefinition = "varchar(255) null comment '邮箱'"
    )
    @ApiModelProperty("邮箱")
    private String email;

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