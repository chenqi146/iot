package com.cqmike.mock.netty;

import com.cqmike.common.dto.MockProductDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
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

    private static Map<String, MockChannel> channelMap = Maps.newConcurrentMap();

    private static List<MockProductDTO> propertyFormList = new ArrayList<>();


    private ChannelMap() {

    }

    public static void put(String deviceSn, MockChannel channel) {
        channelMap.put(deviceSn, channel);
    }

    public synchronized static MockChannel get(String deviceSn) {
        return channelMap.get(deviceSn);
    }

    public static List<MockChannel> values() {
        return Lists.newArrayList(channelMap.values());
    }

    public synchronized static List<MockProductDTO> getPropertyFormList() {
        return propertyFormList;
    }

    public synchronized static void initMockList(List<MockProductDTO> list) {
        propertyFormList.clear();
        propertyFormList.addAll(list);
    }

    public static Map<String, MockChannel> getChannelMap() {
        return channelMap;
    }

}
