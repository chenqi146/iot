package com.cqmike.common.platform.enums;

import com.cqmike.common.base.BaseEnum;

/**
 * @program: iot
 * @EnumName: MiddleTypeEnum
 * @Description: 中间件类型
 * @Author: chen qi
 * @Date: 2020/3/2 21:21
 * @Version: 1.0
 **/
public enum MiddleTypeEnum implements BaseEnum {
    /**
     * mqtt
     */
    MQTT("mqtt"),
    /**
     * kafka
     */
    KAFKA("kafka");

    private final String msg;

    private MiddleTypeEnum(String msg) {
        this.msg = msg;
    }


    @Override
    public String getMsg() {
        return msg;
    }
}
