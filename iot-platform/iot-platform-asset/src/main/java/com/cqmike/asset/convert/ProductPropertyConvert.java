package com.cqmike.asset.convert;
import com.cqmike.asset.entity.ProductProperty;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.core.convert.BaseConvert;
import org.springframework.stereotype.Component;

/**
 * @program: 
 * @Interface: ProductPropertyConvert
 * @Description: ProductPropertyConvert接口类
 * @Author: chen qi
 * @Date: 2020-17-01 17:24
 * @Version: 1.0
**/
@Component
public class ProductPropertyConvert extends BaseConvert<ProductProperty, ProductPropertyForm> {

    @Override
    protected void afterConvertToForm(ProductProperty entity, ProductPropertyForm form) {
        super.afterConvertToForm(entity, form);
    }

    @Override
    protected void afterConvertToEntity(ProductPropertyForm form, ProductProperty entity) {
        super.afterConvertToEntity(form, entity);
    }

}