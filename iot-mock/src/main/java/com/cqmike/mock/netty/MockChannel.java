package com.cqmike.mock.netty;

import cn.hutool.core.map.MapUtil;
import io.netty.channel.Channel;

import java.util.Map;

/**
 * @program: iot
 * @ClassName: MockChannel
 * @Description: 模拟通道类
 * @Author: chen qi
 * @Date: 2020/4/16 19:32
 * @Version: 1.0
 **/
public class MockChannel {

    private Channel channel;
    private long lastTime;
    private Map<String, Long> map;

    public MockChannel(Channel channel) {
        this.channel = channel;
        map = MapUtil.newHashMap();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public long getLastTime(String sn) {
        Long aLong = map.get(sn);
        if (aLong == null) {
            return 0;
        }
        return aLong;
    }

    public void setLastTime(String sn, long lastTime) {
        map.put(sn, lastTime);
    }

    public Map<String, Long> getMap() {
        return map;
    }

    public void setMap(Map<String, Long> map) {
        this.map = map;
    }
}
