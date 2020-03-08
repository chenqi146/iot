package com.cqmike.front.netty;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @program: iot
 * @ClassName: NettyBootstrap
 * @Description: netty启动类
 * @Author: chen qi
 * @Date: 2020/3/7 10:43
 * @Version: 1.0
 **/
@Component
public class NettyBootstrap implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) {
        NettyServer.getInstance().start();
    }
}
