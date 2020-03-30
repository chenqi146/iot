package com.cqmike.common.platform.enums;

import com.cqmike.common.base.BaseEnum;

/**
 * @program: iot
 * @EnumName: RuleStatusEnum
 * @Description: 规则状态
 * @Author: chen qi
 * @Date: 2020/3/2 21:21
 * @Version: 1.0
 **/
public enum RuleStatusEnum implements BaseEnum {
    /**
     * 启用
     */
    ENABLE("启用"),
    /**
     * 停用
     */
    DISABLE("停用");

    private final String msg;

    private RuleStatusEnum(String msg) {
        this.msg = msg;
    }


    @Override
    public String getMsg() {
        return msg;
    }
}
