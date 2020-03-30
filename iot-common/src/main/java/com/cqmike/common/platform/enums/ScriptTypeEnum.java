package com.cqmike.common.platform.enums;

import com.cqmike.common.base.BaseEnum;

/**
 * @program: iot
 * @EnumName: ScriptTypeEnum
 * @Description: 解析脚本类型
 * @Author: chen qi
 * @Date: 2020/3/2 21:21
 * @Version: 1.0
 **/
public enum ScriptTypeEnum implements BaseEnum {
    /**
     * javascript
     */
    JAVASCRIPT("javascript");

    private final String msg;

    private ScriptTypeEnum(String msg) {
        this.msg = msg;
    }


    @Override
    public String getMsg() {
        return msg;
    }
}
