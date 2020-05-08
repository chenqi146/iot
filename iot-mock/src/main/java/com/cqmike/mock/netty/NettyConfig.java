package com.cqmike.mock.netty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: iot
 * @ClassName: NettyConfig
 * @Description: NettyConfig
 * @Author: chen qi
 * @Date: 2020/3/31 20:10
 * @Version: 1.0
 **/
@Configuration
public class NettyConfig {

    @Value("${mock.netty.server.host}")
    private String host;

    @Value("${mock.netty.server.port}")
    private Integer port;

    @Value("${mock.netty.server.count}")
    private Integer count;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
