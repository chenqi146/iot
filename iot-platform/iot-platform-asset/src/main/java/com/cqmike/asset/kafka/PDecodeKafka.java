package com.cqmike.asset.kafka;

import com.cqmike.common.util.KafkaUtil;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @program: iot
 * @ClassName: DecodeKafka
 * @Description: DecodeKafka  kafka序列化和反序列化
 * @Author: chen qi
 * @Date: 2020/5/25 20:13
 * @Version: 1.0
 **/
public class PDecodeKafka<T> implements Deserializer<T> {

    @Override
    public T deserialize(String s, byte[] bytes) {
        return (T) KafkaUtil.byte2Obj(bytes);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }
}
