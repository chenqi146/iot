package com.cqmike.asset.convert;

import com.cqmike.asset.entity.Device;
import com.cqmike.asset.service.ProductService;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.core.convert.BaseConvert;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: 
 * @Interface: DeviceConvert
 * @Description: DeviceConvert接口类
 * @Author: chen qi
 * @Date: 2020-17-01 17:18
 * @Version: 1.0
**/
@Component
public class DeviceConvert extends BaseConvert<Device, DeviceForm> {

    @Resource
    private ProductService productService;

    @Override
    protected void afterConvertToForm(Device entity, DeviceForm form) {
        String productId = form.getProductId();
        ProductForm productForm = productService.findById(productId);
        form.setProductName(productForm.getName());
    }

    @Override
    protected void afterConvertToEntity(DeviceForm form, Device entity) {
        super.afterConvertToEntity(form, entity);
    }

}