package com.cqmike.front.controller;

import com.cqmike.front.service.MqttSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: iot
 * @ClassName: HelloController
 * @Description: TODO
 * @Author: chen qi
 * @Date: 2020/3/3 22:16
 * @Version: 1.0
 **/
@RestController
public class HelloController {

    private final MqttSender mqttSender;

    public HelloController(MqttSender mqttSender) {
        this.mqttSender = mqttSender;
    }

    @RequestMapping("/test/{data}")
    public void test(@PathVariable("data") String data) {
        mqttSender.sendToMqtt(data);
    }
}
