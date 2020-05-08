package com.cqmike.front.netty;

import com.cqmike.front.map.Connection;
import io.netty.util.AttributeKey;

/**
 * @program: sbwork-receive-parent
 * @Description Const
 * @Author 陈琪
 * @Date 2020-03-07 15:56
 * @Version 1.0
 **/
public class Const {

    /**
     * 读超时  秒
     */
    public static final int READ_IDLE_TIME_OUT = 0;
    /**
     *  写超时
     */
    public static final int WRITE_IDLE_TIME_OUT = 0;
    /**
     *  所有超时
     */
    public static final int ALL_IDLE_TIME_OUT = 0;


    /**
     * channel对应的连接
     */
    public static final AttributeKey<Connection> CONNECTION = AttributeKey
            .valueOf("connection");

    public static final int DEVICE_DATA_MIN_LENGTH = 10;
    public static final int DEVICE_SN_LENGTH = 10;
}
