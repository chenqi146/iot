package com.cqmike.common.platform.enums;

import com.cqmike.common.base.BaseEnum;

/**
 * @program: iot
 * @EnumName: ProductTypeEnum
 * @Description: 产品类型枚举
 * @Author: chen qi
 * @Date: 2020/3/2 21:21
 * @Version: 1.0
 **/
public enum ProductTypeEnum implements BaseEnum {
    /**
     * 网关
     */
    GATEWAY("网关"),
    /**
     * 子设备
     */
    CHILD_DEVICE("子设备"),
    /**
     * 设备
     */
    DEVICE("设备");

    private final String msg;

    private ProductTypeEnum(String msg) {
        this.msg = msg;
    }


    @Override
    public String getMsg() {
        return msg;
    }
}
