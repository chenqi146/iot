package com.cqmike.user.service.impl;

import cn.hutool.core.codec.Base32;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cqmike.core.exception.BusinessException;
import com.cqmike.core.service.AbstractCurdService;
import com.cqmike.user.convert.UserConvert;
import com.cqmike.user.entity.User;
import com.cqmike.common.platform.form.UserForm;
import com.cqmike.common.platform.form.search.UserSearchForm;
import com.cqmike.user.interceptor.Auth;
import com.cqmike.user.repository.UserRepository;
import com.cqmike.user.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @program: 
 * @Interface: UserServiceImpl
 * @Description: UserServiceImpl实现类
 * @Author: chen qi
 * @Date: 2020-17-22 17:58
 * @Version: 1.0
**/

@Service
public class UserServiceImpl extends AbstractCurdService<User, String, UserSearchForm, UserForm> implements UserService {

    private final UserRepository repository;
    private final StringRedisTemplate redisTemplate;
    
    private final UserConvert convert;

    public UserServiceImpl(UserRepository repository,
                           StringRedisTemplate redisTemplate,
                           UserConvert convert) {
        super(repository, convert);
        this.repository = repository;
        this.redisTemplate = redisTemplate;
        this.convert = convert;
    }

    @Override
    public UserForm findOneByLoginName(String loginName) {

        if (StrUtil.isEmpty(loginName)) {
            return null;
        }

        User search = new User();
        search.setLoginName(loginName);
        Example<User> userExample = Example.of(search);
        Optional<User> one = repository.findOne(userExample);
        return one.map(convert::convertToForm).orElse(null);

    }

    @Override
    public String login(String loginName, String password) {

        UserForm userForm = this.findOneByLoginName(loginName);
        if (userForm == null) {
            throw new BusinessException("用户或密码不正确");
        }

        String pwdStr = Base32.decodeStr(password);
        pwdStr = SecureUtil.md5(pwdStr);
        boolean equals = pwdStr.equals(userForm.getPassword());
        if (!equals) {
            throw new BusinessException("用户或密码不正确");
        }

        String token = JWT.create()
                .withClaim("loginName", loginName)
                .sign(Algorithm.HMAC256(password));

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(loginName, token, 2, TimeUnit.HOURS);
        return token;
    }

    @Override
    public void logout() {
        UserForm user = Auth.getInstance().getUser();
        redisTemplate.delete(user.getLoginName());
    }

    @NonNull
    @Override
    public UserForm create(@NonNull UserForm form) {

        String password = form.getPassword();
        password = Base32.decodeStr(password);
        password = SecureUtil.md5(password);
        form.setPassword(password);

        return super.create(form);
    }
}