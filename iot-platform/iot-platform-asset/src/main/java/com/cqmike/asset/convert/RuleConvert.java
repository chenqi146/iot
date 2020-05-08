package com.cqmike.asset.convert;
import com.cqmike.asset.entity.Rule;
import com.cqmike.asset.service.ProductService;
import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.common.platform.form.RuleForm;
import com.cqmike.core.convert.BaseConvert;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: 
 * @Interface: RuleConvert
 * @Description: RuleConvert接口类
 * @Author: chen qi
 * @Date: 2020-17-01 17:24
 * @Version: 1.0
**/
@Component
public class RuleConvert extends BaseConvert<Rule, RuleForm> {

    @Resource
    private ProductService productService;

    @Override
    protected void afterConvertToForm(Rule entity, RuleForm form) {
        ProductForm productForm = productService.findById(form.getProductId());
        form.setProductName(productForm.getName());
    }

    @Override
    protected void afterConvertToEntity(RuleForm form, Rule entity) {
        super.afterConvertToEntity(form, entity);
    }

}