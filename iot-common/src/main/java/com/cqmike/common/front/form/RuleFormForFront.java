package com.cqmike.common.front.form;

import com.cqmike.common.platform.enums.MiddleTypeEnum;
import com.cqmike.common.platform.enums.RuleTypeEnum;
import com.cqmike.core.form.BaseForm;
import lombok.*;

/**
 * @program: iot
 * @ClassName: RuleFormForFront
 * @Description: 规则
 * @Author: chen qi
 * @Date: 2020/3/10 21:13
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RuleFormForFront extends BaseForm {

    private RuleTypeEnum ruleType;
    private String topic;
    private MiddleTypeEnum middleType;
    private String productId;
}
