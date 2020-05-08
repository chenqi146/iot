package com.cqmike.user.service;
import com.cqmike.core.service.CurdService;
import com.cqmike.common.platform.form.UserForm;
import com.cqmike.common.platform.form.search.UserSearchForm;

/**
 * @program: 
 * @Interface: UserService
 * @Description: UserService接口类
 * @Author: chen qi
 * @Date: 2020-17-22 17:58
 * @Version: 1.0
**/
public interface UserService extends CurdService<UserForm, String, UserSearchForm> {

    /**
     *  根据用户名查询用户
     * @param loginName
     * @return
     */
    UserForm findOneByLoginName(String loginName);


    /**
     *  登陆
     * @param loginName
     * @param password
     * @return
     */
    String login(String loginName, String password);

    /**
     *  登出
     */
    void logout();

}