package com.cqmike.user.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.cqmike.common.platform.form.UserForm;
import com.cqmike.common.platform.form.search.UserSearchForm;
import com.cqmike.core.controller.BaseController;
import com.cqmike.core.result.ReturnForm;
import com.cqmike.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: 
 * @Interface: UserController
 * @Description: UserController控制层类
 * @Author: chen qi
 * @Date: 2020-17-22 17:58
 * @Version: 1.0
**/
@RestController
@Api(tags = {"用户接口"})
public class UserController extends BaseController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    
    @ApiOperation("findById")
    @GetMapping("/user/{id}")
    public ReturnForm<UserForm> findById(@PathVariable("id") String id) {
        UserForm result = service.findById(id);
        result.setPassword(null);
        return ReturnForm.success(result);
    }

    @ApiOperation("removeById")
    @DeleteMapping("/user/{id}")
    public ReturnForm<UserForm> removeById(@PathVariable("id") String id) {
        UserForm result = service.removeById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("listAllBySearchForm")
    @GetMapping("/users")
    public ReturnForm<?> listAllBySearchForm(UserSearchForm searchForm) {
        Integer page = searchForm.getPage();
        Integer size = searchForm.getSize();
        if (page == null && size == null) {
            List<UserForm> formList = service.listAllBySearchForm(searchForm);
            formList.forEach(e -> e.setPassword(null));
            return ReturnForm.success(formList);
        }
        Page<UserForm> formList = service.listAllBySearchFormPage(searchForm);
        List<UserForm> content = formList.getContent();
        if (CollectionUtil.isEmpty(content)) {
            return ReturnForm.success(formList);
        }
        content.forEach(e -> e.setPassword(null));
        return ReturnForm.success(formList);
    }

    @ApiOperation("create")
    @PostMapping("/user/create")
    public ReturnForm<UserForm> create(UserForm form) {
        UserForm result = service.create(form);
        result.setPassword(null);
        return ReturnForm.success(result);
    }

    @ApiOperation("update")
    @PutMapping("/user")
    public ReturnForm<UserForm> update(UserForm form) {
        UserForm result = service.update(form);
        result.setPassword(null);
        return ReturnForm.success(result);
    }

    @ApiOperation("remove")
    @DeleteMapping("/user")
    public ReturnForm<UserForm> remove(UserForm form) {
        service.remove(form);
        return ReturnForm.success();
    }

    @ApiOperation("登陆接口")
    @PostMapping("/user/login")
    public ReturnForm<Map<String, String>> login(@NotNull String loginName, @NotNull String password) {

        String token = service.login(loginName, password);
        HashMap<String, String> map = MapUtil.newHashMap();
        map.put("token", token);
        return ReturnForm.success(map);
    }

    @ApiOperation("登出接口")
    @PostMapping("/user/logout")
    public ReturnForm<UserForm> logout() {
        service.logout();
        return ReturnForm.success();
    }

}