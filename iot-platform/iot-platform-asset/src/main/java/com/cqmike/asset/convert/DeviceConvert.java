package com.cqmike.asset.convert;

import com.cqmike.asset.entity.Device;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.core.convert.BaseConvert;
import org.springframework.stereotype.Component;

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

    @Override
    protected void afterConvertToForm(Device entity, DeviceForm form) {
        super.afterConvertToForm(entity, form);
    }

    @Override
    protected void afterConvertToEntity(DeviceForm form, Device entity) {
        super.afterConvertToEntity(form, entity);
    }

}