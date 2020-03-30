package com.cqmike.common.platform.enums;

import com.cqmike.common.base.BaseEnum;

/**
 * @program: iot
 * @EnumName: DeviceOperateTypeEnum
 * @Description: 设备操作类型
 * @Author: chen qi
 * @Date: 2020/3/2 21:21
 * @Version: 1.0
 **/
public enum DeviceOperateTypeEnum implements BaseEnum {
    /**
     * 创建create
     */
    CREATE("创建"),
    /**
     * 删除
     */
    DELETE("删除"),
    /**
     * 更新
     */
    UPDATE("更新");

    private final String msg;

    private DeviceOperateTypeEnum(String msg) {
        this.msg = msg;
    }


    @Override
    public String getMsg() {
        return msg;
    }
}
