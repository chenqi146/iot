package com.cqmike.front.map;

import com.cqmike.common.front.form.DeviceFormForFront;
import com.cqmike.front.netty.Const;
import io.netty.channel.Channel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: iot
 * @ClassName: Connection
 * @Description: 连接管理类
 * @Author: chen qi
 * @Date: 2020/3/7 15:56
 * @Version: 1.0
 **/
@Slf4j
@Data
public class Connection {

    private Channel channel;

    private String deviceSn;

    private DeviceFormForFront deviceFormForFront;

    public Connection(Channel channel, String deviceSn, DeviceFormForFront deviceFormForFront) {
        this.channel = channel;
        this.deviceSn = deviceSn;
        this.deviceFormForFront = deviceFormForFront;
    }

    public void register(Connection conn) {
        conn.getChannel().attr(Const.CONNECTION).set(conn);
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
}
