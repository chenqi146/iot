package com.cqmike.user.convert;
import com.cqmike.common.platform.form.UserForm;
import com.cqmike.user.entity.User;
import com.cqmike.core.convert.BaseConvert;
import org.springframework.stereotype.Component;

/**
 * @program: 
 * @Interface: UserConvert
 * @Description: UserConvert接口类
 * @Author: chen qi
 * @Date: 2020-17-22 17:58
 * @Version: 1.0
**/
@Component
public class UserConvert extends BaseConvert<User, UserForm> {

    @Override
    protected void afterConvertToForm(User entity, UserForm form) {
        super.afterConvertToForm(entity, form);
    }

    @Override
    protected void afterConvertToEntity(UserForm form, User entity) {
        super.afterConvertToEntity(form, entity);
    }

}