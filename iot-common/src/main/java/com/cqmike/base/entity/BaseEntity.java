package com.cqmike.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: iot
 * @Description 基类
 * @Author 陈琪
 * @Date 2020-2-16 0016 16:30
 * @Version 1.0
 **/
public class BaseEntity implements Serializable {

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @Column(
            name = "create_time",
            columnDefinition = "timestamp COMMENT '创建时间'"
            )
    @ApiModelProperty(
            hidden = true
    )
    @CreationTimestamp
    private Date createTime;

    @Column(
            name = "create_user_name",
            columnDefinition = "varchar(32) COMMENT '创建用户'"
    )
    @ApiModelProperty(
            hidden = true
    )
    private String createUserName;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @Column(
            name = "create_time",
            columnDefinition = "timestamp COMMENT '最后修改时间'"
    )
    @ApiModelProperty(
            hidden = true
    )
    @UpdateTimestamp
    private Date lastModifyTime;

    @Column(
            name = "create_user_name",
            columnDefinition = "varchar(32) COMMENT '最后修改用户'"
    )
    @ApiModelProperty(
            hidden = true
    )
    private String lastModifyUserName;

    public BaseEntity() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }
}
