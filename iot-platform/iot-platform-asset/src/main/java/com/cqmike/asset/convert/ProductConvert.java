package com.cqmike.asset.convert;

import com.cqmike.asset.entity.Device;
import com.cqmike.asset.entity.Product;
import com.cqmike.asset.repository.DeviceRepository;
import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.core.convert.BaseConvert;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program:
 * @Interface: ProductConvert
 * @Description: ProductConvert接口类
 * @Author: chen qi
 * @Date: 2020-17-01 17:24
 * @Version: 1.0
 **/
@Component
public class ProductConvert extends BaseConvert<Product, ProductForm> {

    @Resource
    private DeviceRepository deviceRepository;

    @Override
    protected void afterConvertToForm(Product entity, ProductForm form) {
        // 统计设备数量
        Device device = new Device();
        device.setProductId(entity.getId());
        Example<Device> e = Example.of(device);
        long count = deviceRepository.count(e);
        form.setDeviceNumber((int) count);
    }

    @Override
    protected void afterConvertToEntity(ProductForm form, Product entity) {
        super.afterConvertToEntity(form, entity);
    }

}