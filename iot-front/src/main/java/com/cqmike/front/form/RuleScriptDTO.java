package com.cqmike.front.form;

import com.cqmike.asset.form.front.ParserFormForFront;
import com.cqmike.asset.form.front.RuleFormForFront;
import com.cqmike.front.emnus.OperateTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: iot
 * @ClassName: RuleScriptDTO
 * @Description: 规则和脚本传输对象
 * @Author: chen qi
 * @Date: 2020/3/19 20:38
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleScriptDTO {

    private OperateTypeEnum operateType;
    private RuleFormForFront ruleForm;
    private ParserFormForFront parserForm;
    private String productId;
}
