package com.cqmike.user.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cqmike.common.platform.form.UserForm;
import com.cqmike.core.exception.ApiAuthorityException;
import com.cqmike.user.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @program: iot
 * @ClassName: AuthenticationInterceptor
 * @Description: 拦截器  验证token
 * @Author: chen qi
 * @Date: 2020/3/23 17:42
 * @Version: 1.0
 **/
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            throw new ApiAuthorityException("接口没有携带token");
        }

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String feignToken = ops.get("feignToken");
        if (StrUtil.isEmpty(feignToken)) {
            throw new ApiAuthorityException("feignToken失效");
        }
        
        if (feignToken.equals(token)) {
            ops.set("feignToken", feignToken, 2, TimeUnit.HOURS);
            return true;
        }

        String loginName = JWT.decode(token).getClaim("loginName").asString();
        if (StrUtil.isEmpty(loginName)) {
            throw new ApiAuthorityException("token无效");
        }

        UserForm user = userService.findOneByLoginName(loginName);
        if (user == null) {
            throw new ApiAuthorityException("没有对应的用户存在");
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ApiAuthorityException("token校验出错");
        }

        String redisToken = ops.get(loginName);
        if (StrUtil.isEmpty(redisToken)) {
            throw new ApiAuthorityException("token过期, 请重新登陆");
        }

        if (!redisToken.equals(token)) {
            throw new ApiAuthorityException("token失效, 请重新登陆");
        }

        ops.set(loginName, token, 2, TimeUnit.HOURS);
        user.setPassword(null);
        Auth.getInstance().setUser(user);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Auth.getInstance().setUser(null);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
