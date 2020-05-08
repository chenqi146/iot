package com.cqmike.asset.convert;
import com.cqmike.asset.entity.DeviceLog;
import com.cqmike.common.platform.form.DeviceLogForm;
import com.cqmike.core.convert.BaseConvert;
import org.springframework.stereotype.Component;

/**
 * @program: 
 * @Interface: DeviceLogConvert
 * @Description: DeviceLogConvert接口类
 * @Author: chen qi
 * @Date: 2020-17-01 17:20
 * @Version: 1.0
**/
@Component
public class DeviceLogConvert extends BaseConvert<DeviceLog, DeviceLogForm> {

    @Override
    protected void afterConvertToForm(DeviceLog entity, DeviceLogForm form) {
        super.afterConvertToForm(entity, form);
    }

    @Override
    protected void afterConvertToEntity(DeviceLogForm form, DeviceLog entity) {
        super.afterConvertToEntity(form, entity);
    }

}