package com.cqmike.common.platform.form;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: 
 * @Interface: ProductPropertyParserForm
 * @Description: ProductPropertyParserForm表单类
 * @Author: chen qi
 * @Date: 2020-15-07 15:38
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ProductPropertyParserForm")
@EqualsAndHashCode(callSuper = true)
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


}