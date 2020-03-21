package com.cqmike.front.emnus;

import com.cqmike.asset.enums.BaseEnum;

/**
 * @program: iot
 * @EnumName: OperateTypeEnum
 * @Description: rule和js操作
 * @Author: chen qi
 * @Date: 2020/3/2 21:21
 * @Version: 1.0
 **/
public enum OperateTypeEnum implements BaseEnum {
    /**
     * 创建create
     */
    CREATE("创建"),
    /**
     * 删除全部
     */
    DELETE_ALL("删除全部"),
    /**
     * 删除
     */
    DELETE("删除"),
    /**
     * 更新
     */
    UPDATE("更新");

    private final String msg;

    private OperateTypeEnum(String msg) {
        this.msg = msg;
    }


    @Override
    public String getMsg() {
        return msg;
    }
}
