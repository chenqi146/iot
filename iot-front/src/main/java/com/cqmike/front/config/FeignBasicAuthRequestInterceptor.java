package com.cqmike.front.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @program: iot
 * @ClassName: FeignBasicAuthRequestInterceptor
 * @Description: Feign请求拦截器
 * @Author: chen qi
 * @Date: 2020/3/30 21:58
 * @Version: 1.0
 **/
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    private String token;

    public FeignBasicAuthRequestInterceptor() {
    }

    public FeignBasicAuthRequestInterceptor(String token) {
        this.token = token;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 设置请求头的token
        requestTemplate.header("token", token);
    }
}
