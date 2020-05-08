package com.cqmike.user.interceptor;

import com.cqmike.common.platform.form.UserForm;

/**
 * @program: iot
 * @ClassName: Auth
 * @Description: 用户线程变量
 * @Author: chen qi
 * @Date: 2020/3/23 18:11
 * @Version: 1.0
 **/
public class Auth {

    private final ThreadLocal<UserForm> threadLocal = new ThreadLocal<>();

    private Auth() {
    }

    public static Auth getInstance() {
        return AuthInstance.INSTANCE;
    }

    private static class AuthInstance {
        private final static Auth INSTANCE = new Auth();
    }

    public void setUser(UserForm userForm) {
        threadLocal.set(userForm);
    }

    public UserForm getUser() {
        return threadLocal.get();
    }

    public void remove() {
        threadLocal.remove();
    }
}
