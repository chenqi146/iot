package com.cqmike.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @program: iot
 * @ClassName: Message
 * @Description: 消息类  消息传输类
 * @Author: chen qi
 * @Date: 2020/3/6 21:32
 * @Version: 1.0
 **/
public class Message implements Serializable {

    private static final long serialVersionUID = 5553434835281973556L;
    private String id;
    private Object msg;
    private Date date;


    public Message() {
    }

    public Message(Object msg) {
        this.date = new Date();
        this.id = UUID.randomUUID().toString();
//        this.msg = JsonUtils.toJson(msg);
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
