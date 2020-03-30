package com.cqmike.common.dto;

import com.cqmike.common.front.enums.OperateTypeEnum;
import com.cqmike.common.front.form.ParserFormForFront;
import com.cqmike.common.front.form.RuleFormForFront;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleScriptDTO {

    private OperateTypeEnum operateType;
    private RuleFormForFront ruleForm;
    private ParserFormForFront parserForm;
    private String productId;
}
