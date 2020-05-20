package com.cqmike.common.platform.enums;

import com.cqmike.common.base.BaseEnum;

/**
 * @program: iot
 * @EnumName: AccessTypeEnum
 * @Description: 规则类型
 * @Author: chen qi
 * @Date: 2020/3/2 21:21
 * @Version: 1.0
 **/
public enum RuleTypeEnum implements BaseEnum {
    /**
     * 流转
     */
    CIRCULATION("流转");

    private final String msg;

    private RuleTypeEnum(String msg) {
        this.msg = msg;
    }


    @Override
    public String getMsg() {
        return msg;
    }
}
