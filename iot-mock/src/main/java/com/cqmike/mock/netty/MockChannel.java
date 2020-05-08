package com.cqmike.mock.netty;

import io.netty.channel.Channel;

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

    public MockChannel(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }
}
