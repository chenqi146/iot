package com.cqmike.front.config;

import com.cqmike.core.util.JsonUtils;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @program: iot
 * @ClassName: Message
 * @Description: 消息类
 * @Author: chen qi
 * @Date: 2020/3/6 21:32
 * @Version: 1.0
 **/
@Data
public class Message {

    private String id;
    private String msg;
    private Date date;

    public Message(Object msg) {
        this.date = new Date();
        this.id = UUID.randomUUID().toString();
        this.msg = JsonUtils.toJson(msg);
    }
}
