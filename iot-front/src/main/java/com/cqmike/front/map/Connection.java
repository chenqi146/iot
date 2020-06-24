package com.cqmike.front.map;

import com.cqmike.common.front.form.DeviceFormForFront;
import com.cqmike.front.netty.Const;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: iot
 * @ClassName: Connection
 * @Description: 连接管理类
 * @Author: chen qi
 * @Date: 2020/3/7 15:56
 * @Version: 1.0
 **/
public class Connection {

    private static final Logger log = LoggerFactory.getLogger(Connection.class);

    private Channel channel;

    private String deviceSn;

    private DeviceFormForFront deviceFormForFront;

    public Connection(Channel channel, String deviceSn, DeviceFormForFront deviceFormForFront) {
        this.channel = channel;
        this.deviceSn = deviceSn;
        this.deviceFormForFront = deviceFormForFront;
    }

    /**
     *  注册
     * @param conn
     */
    public void register(Connection conn) {
        // netty的属性
        conn.getChannel().attr(Const.CONNECTION).set(conn);
        // 设备通道关系管理类注册
        DeviceChannelRel.addConnection(conn);
    }

    public void logOut() {
        DeviceChannelRel.removeConnection(this);
        this.getChannel().attr(Const.CONNECTION).set(null);
        this.channel.close();
    }

    public boolean containsConnection(String deviceSn) {
        return DeviceChannelRel.containsConnection(deviceSn);
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public DeviceFormForFront getDeviceFormForFront() {
        return deviceFormForFront;
    }

    public void setDeviceFormForFront(DeviceFormForFront deviceFormForFront) {
        this.deviceFormForFront = deviceFormForFront;
    }
}
