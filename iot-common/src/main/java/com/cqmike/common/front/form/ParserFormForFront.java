package com.cqmike.common.front.form;

import com.cqmike.core.form.BaseForm;

/**
 * @program: iot
 * @ClassName: ParserFormForFront
 * @Description: 前置机解析类
 * @Author: chen qi
 * @Date: 2020/3/21 10:02
 * @Version: 1.0
 **/
public class ParserFormForFront extends BaseForm {

    /**
     * 产品id
     */
    private String productId;

    /**
     * 解析脚本
     */
    private String script;

    /**
     * 脚本类型  javascript
     */
    private String scriptType;

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

    @Override
    public String toString() {
        return "ParserFormForFront{" +
                "productId='" + productId + '\'' +
                ", script='" + script + '\'' +
                ", scriptType='" + scriptType + '\'' +
                '}';
    }
}
