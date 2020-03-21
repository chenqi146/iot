package com.cqmike.front.schedule;

import com.cqmike.asset.form.front.DeviceFormForFront;
import com.cqmike.asset.service.DeviceService;
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
    private DeviceService deviceService;

    /**
     *  todo  十秒拉取一次接口   后续想想推送方式
     */
    @Scheduled(fixedRate = 10 * 1000)
    public void getDeviceFormForFront() {
        List<Connection> connections = DeviceChannelRel.getConnections();
        for (Connection connection : connections) {
            String deviceSn = connection.getDeviceSn();
            DeviceFormForFront form = deviceService.findDeviceForFrontBySn(deviceSn);
            connection.setDeviceFormForFront(form);
        }
    }

}
