package com.cqmike.front.schedule;

import com.cqmike.common.front.form.DeviceFormForFront;
import com.cqmike.core.result.ReturnForm;
import com.cqmike.front.client.PlatformClient;
import com.cqmike.front.map.Connection;
import com.cqmike.front.map.DeviceChannelRel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: iot
 * @ClassName: schedule
 * @Description: FrontSchedule
 * @Author: chen qi
 * @Date: 2020/3/21 10:15
 * @Version: 1.0
 **/
@Component
public class FrontSchedule {

    @Resource
    private PlatformClient platformClient;

    /**
     *  获取设备信息 10s
     */
    @Scheduled(fixedRate = 10 * 1000)
    public void getDeviceFormForFront() {
        // 获取所有连接通道
        List<Connection> connections = DeviceChannelRel.getConnections();
        for (Connection connection : connections) {
            // 更新设备信息
            String deviceSn = connection.getDeviceSn();
            ReturnForm<DeviceFormForFront> form = platformClient.findDeviceForFrontBySn(deviceSn);
            DeviceFormForFront message = form.getMessage();
            connection.setDeviceFormForFront(message);
        }
    }

}
