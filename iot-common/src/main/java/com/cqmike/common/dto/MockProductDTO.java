package com.cqmike.common.dto;

import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.common.platform.form.ProductPropertyForm;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @program: iot
 * @ClassName: MockProductDTO
 * @Description: mock 产品对象  里面没有网关产品
 * @Author: chen qi
 * @Date: 2020/4/16 18:01
 * @Version: 1.0
 **/
public class MockProductDTO extends ProductForm implements Serializable {

    /**
     * 下属设备List
      */
    private List<DeviceForm> deviceFormList;

    /**
     *  k - 设备id  v - 父设备网关sn
     */
    private Map<String, String> childDeviceMapperMap;

    private List<ProductPropertyForm> propertyFormList;

    public List<DeviceForm> getDeviceFormList() {
        return deviceFormList;
    }

    public void setDeviceFormList(List<DeviceForm> deviceFormList) {
        this.deviceFormList = deviceFormList;
    }

    public Map<String, String> getChildDeviceMapperMap() {
        return childDeviceMapperMap;
    }

    public void setChildDeviceMapperMap(Map<String, String> childDeviceMapperMap) {
        this.childDeviceMapperMap = childDeviceMapperMap;
    }

    public List<ProductPropertyForm> getPropertyFormList() {
        return propertyFormList;
    }

    public void setPropertyFormList(List<ProductPropertyForm> propertyFormList) {
        this.propertyFormList = propertyFormList;
    }
}
