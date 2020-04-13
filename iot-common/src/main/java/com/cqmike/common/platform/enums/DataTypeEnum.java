package com.cqmike.common.platform.enums;

import com.cqmike.common.base.BaseEnum;
import com.cqmike.common.platform.form.ProductPropertyForm;

/**
 * @program: iot
 * @EnumName: DataTypeEnum
 * @Description: 数据类型
 * @Author: chen qi
 * @Date: 2020/4/3 17:26
 * @Version: 1.0
 **/
public enum DataTypeEnum implements BaseEnum {

    /**
     * 整型
     */
    INTEGER(Integer.class, "Integer(整型)") {
        @Override
        public boolean verify(ProductPropertyForm form, Object o) {
            return this.compareBestValue(form.getMax(), form.getMin(), o);
        }

    },
    /**
     * LONG
     */
    LONG(Long.class, "Long(长整型)") {
        @Override
        public boolean verify(ProductPropertyForm form, Object o) {
            return this.compareBestValue(form.getMax(), form.getMin(), o);
        }

    },
    /**
     * STRING
     */
    STRING(String.class, "String(字符串型)") {
        @Override
        public boolean verify(ProductPropertyForm form, Object o) {
            return false;
        }

    },
    /**
     * DOUBLE
     */
    DOUBLE(Double.class, "Double(双精度型)") {
        @Override
        public boolean verify(ProductPropertyForm form, Object o) {
            return this.compareBestValue(form.getMax(), form.getMin(), o);
        }

    },
    /**
     * FLOAT
     */
    FLOAT(Float.class, "Float(浮点型)") {
        @Override
        public boolean verify(ProductPropertyForm form, Object o) {
            return this.compareBestValue(form.getMax(), form.getMin(), o);
        }

    };

    private Class<?> aClass;
    private String msg;

    private DataTypeEnum(Class<?> aClass, String msg) {
        this.aClass = aClass;
        this.msg = msg;
    }



    /**
     * 校验
     * @return
     */
    public abstract boolean verify(ProductPropertyForm form, Object o);

    /**
     * 转换并且获取值
     * @param aClass
     * @param o
     * @param <T>
     * @return
     */
    public <T> T transformValue(Class<T> aClass, Object o){
        return aClass.cast(o);
    }


    @Override
    public String getMsg() {
        return msg;
    }

    public Class<?> getAclass() {
        return aClass;
    }

    protected boolean compareBestValue(Double max, Double min, Object value) {
        Double v = (Double) value;
        if (v > max) {
            return false;
        }
        return v >= min;

    }
}
