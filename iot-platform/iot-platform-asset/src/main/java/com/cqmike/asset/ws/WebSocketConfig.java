package com.cqmike.asset.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @program: iot
 * @ClassName: WebSocketConfig
 * @Description: webSocket配置类
 * @Author: chen qi
 * @Date: 2020/3/21 17:49
 * @Version: 1.0
 **/
@Configuration
public class WebSocketConfig  {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
