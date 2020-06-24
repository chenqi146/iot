package com.cqmike.common.dto;


/**
 * @program: iot
 * @ClassName: SocketMessage
 * @Description: SocketMessage websocket传输对象
 * @Author: chen qi
 * @Date: 2020/3/21 18:03
 * @Version: 1.0
 **/
public class SocketMessage {

    /**
     *  0-设备离线
     *  1-设备上线
     *  2-设备数据
     */
    private Integer msgType;
    private long msgTime;
    private Object msgData;

    public SocketMessage(Integer msgType, Object msgData) {
        this.msgType = msgType;
        this.msgTime = System.currentTimeMillis();
        this.msgData = msgData;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }

    public Object getMsgData() {
        return msgData;
    }

    public void setMsgData(Object msgData) {
        this.msgData = msgData;
    }
}
