package com.cqmike.asset.convert;
import com.cqmike.asset.entity.ProductPropertyParser;
import com.cqmike.common.platform.form.ProductPropertyParserForm;
import com.cqmike.core.convert.BaseConvert;
import org.springframework.stereotype.Component;

/**
 * @program: 
 * @Interface: ProductPropertyParserConvert
 * @Description: ProductPropertyParserConvert接口类
 * @Author: chen qi
 * @Date: 2020-17-01 17:24
 * @Version: 1.0
**/
@Component
public class ProductPropertyParserConvert extends BaseConvert<ProductPropertyParser, ProductPropertyParserForm> {

    @Override
    protected void afterConvertToForm(ProductPropertyParser entity, ProductPropertyParserForm form) {
        super.afterConvertToForm(entity, form);
    }

    @Override
    protected void afterConvertToEntity(ProductPropertyParserForm form, ProductPropertyParser entity) {
        super.afterConvertToEntity(form, entity);
    }

}