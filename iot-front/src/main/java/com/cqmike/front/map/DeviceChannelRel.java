package com.cqmike.front.map;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: iot
 * @ClassName: DeviceChannelRel
 * @Description: 存储Channel
 * @Author: chen qi
 * @Date: 2020/3/7 17:03
 * @Version: 1.0
 **/
public class DeviceChannelRel {

    private static final ConcurrentHashMap<String, Connection> manager = new ConcurrentHashMap<>();

    public static void addConnection(Connection connection) {
        synchronized (manager) {
            manager.put(connection.getDeviceSn(), connection);
        }
    }

    public static Connection getConnection(String sn) {
        return manager.get(sn);
    }

    public static void removeConnection(Connection connection) {
        synchronized (manager) {
            if (containsConnection(connection.getDeviceSn())) {
                manager.remove(connection.getDeviceSn());
            }
        }
    }

    public static boolean containsConnection(String deviceSn) {
        return manager.containsKey(deviceSn);
    }

    public static List<Connection> getConnections(){
        return new ArrayList<>(manager.values());
    }

    public static int size() {
        return manager.size();
    }

}
