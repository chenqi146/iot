package com.cqmike.common.front.form;

import com.cqmike.core.form.BaseForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: iot
 * @ClassName: ParserFormForFront
 * @Description: 前置机解析类
 * @Author: chen qi
 * @Date: 2020/3/21 10:02
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
}
