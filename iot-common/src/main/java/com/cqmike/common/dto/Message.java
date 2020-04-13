package com.cqmike.common.dto;

import com.cqmike.core.util.JsonUtils;

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
public class Message {

    private String id;
    private String msg;
    private Date date;

    public Message(Object msg) {
        this.date = new Date();
        this.id = UUID.randomUUID().toString();
        this.msg = JsonUtils.toJson(msg);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
