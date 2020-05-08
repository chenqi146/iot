package com.cqmike.asset.entity;

import com.cqmike.common.platform.enums.ScriptTypeEnum;
import com.cqmike.core.entity.BaseEntity;
import com.cqmike.user.interceptor.CustomerListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @program: 
 * @Interface: ProductPropertyParserEntity
 * @Description: ProductPropertyParserEntity实体
 * @Author: chen qi
 * @Date: 2020-15-07 15:38
 * @Version: 1.0
**/
@Entity
@Table(name = "product_property_parser")
@ApiModel(value = "产品属性解析实体")
@EntityListeners(value = {CustomerListener.class})
public class ProductPropertyParser extends BaseEntity {


    /**
     * 产品id
     */
    @Column(
            name = "product_id",
            columnDefinition = "varchar(32) not null comment '产品id'"
    )
    @ApiModelProperty("产品id")
    private String productId;


    /**
     * 解析脚本
     */
    @Column(
            name = "script",
            columnDefinition = "varchar(1024) null comment '解析脚本'"
    )
    @ApiModelProperty("解析脚本")
    private String script;


    /**
     * 脚本类型  javascript
     */
    @Column(
            name = "script_type",
            columnDefinition = "varchar(16) not null comment '脚本类型  javascript'"
    )
    @ApiModelProperty("脚本类型  javascript")
    @Enumerated(EnumType.STRING)
    private ScriptTypeEnum scriptType;

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

    public ScriptTypeEnum getScriptType() {
        return scriptType;
    }

    public void setScriptType(ScriptTypeEnum scriptType) {
        this.scriptType = scriptType;
    }
}