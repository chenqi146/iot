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
 * @Interface: DeviceSearchForm
 * @Description: DeviceSearchForm搜索表单类
 * @Author: chen qi
 * @Date: 2020-19-24 19:42
 * @Version: 1.0
**/
@ApiModel(value = "DeviceSearchForm")
public class DeviceSearchForm extends BaseSearchForm {

    /**
     * 安装日期
     */
    @ApiModelProperty("安装日期")
    private Date installationDate;

    /**
     * 安装地址
     */
    @ApiModelProperty("安装地址")
    private String installationLocation;

    /**
     * 设备名称
     */
    @ApiModelProperty("设备名称")
    private String name;

    /**
     * 产品id
     */
    @ApiModelProperty("产品id")
    private String productId;

    /**
     * 设备sn
     */
    @ApiModelProperty("设备sn")
    private String sn;

    /**
     * 状态  ONLINE-在线  离线
     */
    @ApiModelProperty("状态  ONLINE-在线  离线")
    private String status;

    /**
     * 设备最后上线时间
     */
    @ApiModelProperty("设备最后上线时间")
    private Date lastOnlineTime;

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

    @ApiModelProperty("设备id")
    @Query(value = QueryType.in, columnName = "id")
    private List<String> deviceIdList;


    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public String getInstallationLocation() {
        return installationLocation;
    }

    public void setInstallationLocation(String installationLocation) {
        this.installationLocation = installationLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public List<String> getDeviceIdList() {
        return deviceIdList;
    }

    public void setDeviceIdList(List<String> deviceIdList) {
        this.deviceIdList = deviceIdList;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}