package com.cqmike.asset.convert;
import com.cqmike.asset.entity.Gateway;
import com.cqmike.common.platform.form.GatewayForm;
import com.cqmike.core.convert.BaseConvert;
import org.springframework.stereotype.Component;

/**
 * @program: 
 * @Interface: GatewayConvert
 * @Description: GatewayConvert接口类
 * @Author: chen qi
 * @Date: 2020-16-07 16:22
 * @Version: 1.0
**/
@Component
public class GatewayConvert extends BaseConvert<Gateway, GatewayForm> {

    @Override
    protected void afterConvertToForm(Gateway entity, GatewayForm form) {
        super.afterConvertToForm(entity, form);
    }

    @Override
    protected void afterConvertToEntity(GatewayForm form, Gateway entity) {
        super.afterConvertToEntity(form, entity);
    }

}