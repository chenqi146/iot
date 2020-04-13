package com.cqmike.common.platform.form;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: 
 * @Interface: ProductPropertyParserForm
 * @Description: ProductPropertyParserForm表单类
 * @Author: chen qi
 * @Date: 2020-15-07 15:38
 * @Version: 1.0
**/
@ApiModel(value = "ProductPropertyParserForm")
public class ProductPropertyParserForm extends BaseForm {
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

    public ProductPropertyParserForm() {
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