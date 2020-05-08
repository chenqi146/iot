package com.cqmike.common.platform.enums;

import com.cqmike.common.base.BaseEnum;

/**
 * @program: iot
 * @EnumName: AccessTypeEnum
 * @Description: 接入方式枚举
 * @Author: chen qi
 * @Date: 2020/3/2 21:21
 * @Version: 1.0
 **/
public enum AccessTypeEnum implements BaseEnum {
    /**
     * mqtt
     */
    MQTT("mqtt"),
    /**
     * socket
     */
    SOCKET("socket");

    private final String msg;

    private AccessTypeEnum(String msg) {
        this.msg = msg;
    }


    @Override
    public String getMsg() {
        return msg;
    }
}
