package com.cqmike.common.platform.form.search;

import com.cqmike.core.form.BaseSearchForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @program: 
 * @Interface: ProductPropertyParserSearchForm
 * @Description: ProductPropertyParserSearchForm搜索表单类
 * @Author: chen qi
 * @Date: 2020-15-07 15:38
 * @Version: 1.0
**/
@ApiModel(value = "ProductPropertyParserSearchForm")
public class ProductPropertyParserSearchForm extends BaseSearchForm {

    /**
     * 产品id
     */
    @ApiModelProperty("产品id")
    private String productId;

    /**
     * 解析脚本
     */
    @ApiModelProperty("解析脚本")
    private String script;

    /**
     * 脚本类型  javascript
     */
    @ApiModelProperty("脚本类型  javascript")
    private String scriptType;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getScriptType() {
        return scriptType;
    }

    public void setScriptType(String scriptType) {
        this.scriptType = scriptType;
    }
}