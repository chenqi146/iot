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
 * @Interface: RuleForm
 * @Description: RuleForm表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:24
 * @Version: 1.0
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RuleForm")
@EqualsAndHashCode(callSuper = true)
public class RuleForm extends BaseForm {
    /**
     * 产品id
     */
    @ApiModelProperty("产品id")
    private String productId;

    /**
     * 中间件id
     */
    @ApiModelProperty("中间件id")
    private String middlewareId;

    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    private String name;

    /**
     * 规则类型   流转
     */
    @ApiModelProperty("规则类型   流转")
    private String type;

    /**
     * 规则描述
     */
    @ApiModelProperty("规则描述")
    private String description;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String status;

    /**
     * TOPIC
     */
    @ApiModelProperty("TOPIC")
    private String topic;


}