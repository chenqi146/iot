package com.cqmike.mock.netty;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;

import java.util.List;
import java.util.Map;

/**
 * @program: iot
 * @ClassName: ChannelMap
 * @Description: 通道map
 * @Author: chen qi
 * @Date: 2020/3/25 18:44
 * @Version: 1.0
 **/
public class ChannelMap {

    private static Map<String, Channel> channelMap = Maps.newConcurrentMap();

    private ChannelMap() {

    }

    public static void put(String deviceSn, Channel channel) {
        channelMap.put(deviceSn, channel);
    }

    public static Channel get(String deviceSn) {
        return channelMap.get(deviceSn);
    }

    public static List<Channel> values() {
        return Lists.newArrayList(channelMap.values());
    }

}
