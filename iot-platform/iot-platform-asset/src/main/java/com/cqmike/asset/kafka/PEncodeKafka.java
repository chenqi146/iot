package com.cqmike.asset.kafka;

import com.cqmike.common.util.KafkaUtil;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @program: iot
 * @ClassName: DecodeKafka
 * @Description: DecodeKafka
 * @Author: chen qi
 * @Date: 2020/5/25 20:13
 * @Version: 1.0
 **/
public class PEncodeKafka<T> implements Serializer<T> {

    @Override
    public byte[] serialize(String s, T data) {
        return KafkaUtil.bean2Byte(data);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }
}
