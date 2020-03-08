package com.cqmike.front.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @program: iot
 * @ClassName: DeviceDataTimeForm
 * @Description: 记录设备最后一次的时间
 * @Author: chen qi
 * @Date: 2020/3/7 16:48
 * @Version: 1.0
 **/
@Getter
@Setter
public class DeviceDataTimeForm {

    private String deviceSn;
    private Date lastReceiveTime;

    public DeviceDataTimeForm(String deviceSn, Date lastReceiveTime) {
        this.deviceSn = deviceSn;
        this.lastReceiveTime = lastReceiveTime;
    }
}
