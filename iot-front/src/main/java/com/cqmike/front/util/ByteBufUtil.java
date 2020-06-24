package com.cqmike.front.util;

import io.netty.buffer.ByteBuf;

/**
 * @program: iot
 * @ClassName: ByteBufUtil
 * @Description: ByteBufUtil   简单封装的给js调用的静态工具类  由于js对二进制不好操作
 * @Author: chen qi
 * @Date: 2020/5/20 12:37
 * @Version: 1.0
 **/
public final class ByteBufUtil {

    private ByteBufUtil() {
    }

    /**
     *  读取指定长度字符串
     * @param buf
     * @param length
     * @return
     */
    public static String readString(ByteBuf buf, int length) {
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        return new String(bytes);
    }

    public static Double readDouble(ByteBuf buf) {
        return buf.readDouble();
    }
    public static Integer readInteger(ByteBuf buf) {
        return buf.readInt();
    }
    public static Float readFloat(ByteBuf buf) {
        return buf.readFloat();
    }
    public static Long readLong(ByteBuf buf) {
        return buf.readLong();
    }

}
