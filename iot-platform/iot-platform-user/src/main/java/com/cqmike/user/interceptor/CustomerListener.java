package com.cqmike.user.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.cqmike.common.platform.form.UserForm;
import com.cqmike.core.entity.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @program: iot
 * @ClassName: CustomerListener
 * @Description: 实体类 创建人 修改人  修改时间拦截器
 * @Author: chen qi
 * @Date: 2020/3/24 19:59
 * @Version: 1.0
 **/
public class CustomerListener {


    @PrePersist
    public void prePersist(Object source) {
        setUserInfo(source, true);
    }

    @PreUpdate
    public void preUpdate(Object source) {
        setUserInfo(source, false);
    }

    /**
     *  设置用户信息
     * @param source
     * @param isCreate
     */
    private void setUserInfo(Object source, boolean isCreate) {
        if (!(source instanceof BaseEntity)) {
            return;
        }
        UserForm user = Auth.getInstance().getUser();
        if (user != null) {
            if (isCreate) {
                if (StrUtil.isNotEmpty(user.getUserName())) {
                    BeanUtil.setFieldValue(source, "createUserName", user.getUserName());
                }
            }
            if (StrUtil.isNotEmpty(user.getUserName())) {
                BeanUtil.setFieldValue(source, "updateUserName", user.getUserName());
            }
        }
        BeanUtil.setFieldValue(source, "updateTime", new Date());
    }
}
