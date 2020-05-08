package com.cqmike.asset.convert;
import com.cqmike.asset.entity.DeviceRecord;
import com.cqmike.common.platform.form.DeviceRecordForm;
import com.cqmike.core.convert.BaseConvert;
import org.springframework.stereotype.Component;

/**
 * @program: 
 * @Interface: DeviceRecordConvert
 * @Description: DeviceRecordConvert接口类
 * @Author: chen qi
 * @Date: 2020-17-01 17:22
 * @Version: 1.0
**/
@Component
public class DeviceRecordConvert extends BaseConvert<DeviceRecord, DeviceRecordForm> {

    @Override
    protected void afterConvertToForm(DeviceRecord entity, DeviceRecordForm form) {
        super.afterConvertToForm(entity, form);
    }

    @Override
    protected void afterConvertToEntity(DeviceRecordForm form, DeviceRecord entity) {
        super.afterConvertToEntity(form, entity);
    }

}