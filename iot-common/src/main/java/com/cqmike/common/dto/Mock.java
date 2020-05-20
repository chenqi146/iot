package com.cqmike.common.dto;

import java.util.HashMap;

/**
 * @program: iot
 * @ClassName: Mock
 * @Description: mock设备数据对象
 * @Author: chen qi
 * @Date: 2020/4/16 18:12
 * @Version: 1.0
 **/
public class Mock extends HashMap<String, Object> {

    private String deviceSn;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
        this.put("deviceSn", deviceSn);
    }
}
