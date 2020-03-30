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
 * @Interface: ProductPropertyForm
 * @Description: ProductPropertyForm表单类
 * @Author: chen qi
 * @Date: 2020-21-02 21:33
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ProductPropertyForm")
@EqualsAndHashCode(callSuper = true)
public class ProductPropertyForm extends BaseForm {
    /**
     * 属性描述
     */
    @ApiModelProperty("属性描述")
    private String description;

    /**
     * 属性标识符
     */
    @ApiModelProperty("属性标识符")
    private String identifier;

    /**
     * 最大值
     */
    @ApiModelProperty("最大值")
    private Double max;

    /**
     * 最小值
     */
    @ApiModelProperty("最小值")
    private Double min;

    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    private String name;

    /**
     * 产品id
     */
    @ApiModelProperty("产品id")
    private String productId;

    /**
     * 属性类型
     */
    @ApiModelProperty("属性类型")
    private String type;

    /**
     * 属性值
     */
    @ApiModelProperty("属性值")
    private String value;

    /**
     * 属性单位
     */
    @ApiModelProperty("属性单位")
    private String unit;


}