package com.cqmike.common.platform.enums;

import com.cqmike.common.base.BaseEnum;

/**
 * @program: iot
 * @EnumName: DeviceStatusEnum
 * @Description: 设备状态
 * @Author: chen qi
 * @Date: 2020/3/2 21:21
 * @Version: 1.0
 **/
public enum DeviceStatusEnum implements BaseEnum {
    /**
     * ONLINE
     */
    ONLINE("在线"),
    /**
     * OFFLINE
     */
    OFFLINE("离线");

    private final String msg;

    private DeviceStatusEnum(String msg) {
        this.msg = msg;
    }


    @Override
    public String getMsg() {
        return msg;
    }
}
