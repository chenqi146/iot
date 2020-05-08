package com.cqmike.common.platform.enums;

import cn.hutool.core.util.RandomUtil;
import com.cqmike.common.base.BaseEnum;
import com.cqmike.common.platform.form.ProductPropertyForm;

import java.util.Random;

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

        @Override
        public Object mock(ProductPropertyForm form) {
            int min = form.getMin().intValue();
            int max = form.getMax().intValue();
            return RandomUtil.randomInt(min, max);
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

        @Override
        public Object mock(ProductPropertyForm form) {
            long min = form.getMin().longValue();
            long max = form.getMax().longValue();
            return RandomUtil.randomLong(min, max);
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

        @Override
        public Object mock(ProductPropertyForm form) {
            return RandomUtil.randomString(10);
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

        @Override
        public Object mock(ProductPropertyForm form) {
            return RandomUtil.randomDouble(form.getMin(), form.getMax());
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

        @Override
        public Object mock(ProductPropertyForm form) {
            int min = form.getMin().intValue();
            int max = form.getMax().intValue();
            int i = RandomUtil.randomInt(min, max);
            Random random = new Random(System.currentTimeMillis());
            float v = random.nextFloat();
            return v + i;
        }
    };

    private Class<?> aClass;
    private String msg;

    private DataTypeEnum(Class<?> aClass, String msg) {
        this.aClass = aClass;
        this.msg = msg;
    }

    /**
     * 模拟数据
     * @param form
     * @return
     */
    public abstract Object mock(ProductPropertyForm form);
    /**
     * 校验
     *
     * @return
     */
    public abstract boolean verify(ProductPropertyForm form, Object o);

    /**
     * 转换并且获取值
     *
     * @param aClass
     * @param o
     * @param <T>
     * @return
     */
    public <T> T transformValue(Class<T> aClass, Object o) {
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
