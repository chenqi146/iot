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
 * @Interface: MessageMiddlewareParamForm
 * @Description: MessageMiddlewareParamForm表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:22
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MessageMiddlewareParamForm")
@EqualsAndHashCode(callSuper = true)
public class MessageMiddlewareParamForm extends BaseForm {
    /**
     * 参数名称
     */
    @ApiModelProperty("参数名称")
    private String name;

    /**
     * 参数类型
     */
    @ApiModelProperty("参数类型")
    private String type;

    /**
     * 参数值
     */
    @ApiModelProperty("参数值")
    private String value;

    /**
     * 参数描述
     */
    @ApiModelProperty("参数描述")
    private String description;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;


}