package com.cqmike.front.netty;

import com.cqmike.asset.form.front.DeviceFormForFront;
import com.cqmike.front.form.DeviceDataTimeForm;
import io.netty.channel.Channel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @program: iot
 * @ClassName: Connection
 * @Description: TODO
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

    private Map<String, DeviceDataTimeForm> dataTimeFormMap;

    public Connection(Channel channel, String deviceSn, DeviceFormForFront deviceFormForFront) {
        this.channel = channel;
        this.deviceSn = deviceSn;
        this.setDeviceFormForFront(deviceFormForFront);
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

    //todo 重写  设置的时候 生成时间
    public void setDeviceFormForFront(DeviceFormForFront deviceFormForFront) {
        this.deviceFormForFront = deviceFormForFront;
    }
}
