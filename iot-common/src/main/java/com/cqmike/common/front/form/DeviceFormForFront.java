package com.cqmike.common.front.form;

import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.core.form.BaseForm;

import java.util.List;
import java.util.Map;

/**
 * @program: iot
 * @ClassName: DeviceFormForFront
 * @Description: 前置机使用的模型
 * @Author: chen qi
 * @Date: 2020/3/7 16:25
 * @Version: 1.0
 **/
public class DeviceFormForFront extends BaseForm {

    private DeviceForm currentDeviceForm;

    private ProductForm currentProductForm;

    /**
     *  如果为当前设备为网关，此列表为网关子设备列表
     *  k -> deviceSn
     */
    private Map<String, DeviceForm> childDeviceFormMap;

    /**
     *  k-> 产品id
     */
    private Map<String, List<ProductPropertyForm>> propertyMap;

    public DeviceForm getCurrentDeviceForm() {
        return currentDeviceForm;
    }

    public void setCurrentDeviceForm(DeviceForm currentDeviceForm) {
        this.currentDeviceForm = currentDeviceForm;
    }

    public ProductForm getCurrentProductForm() {
        return currentProductForm;
    }

    public void setCurrentProductForm(ProductForm currentProductForm) {
        this.currentProductForm = currentProductForm;
    }

    public Map<String, DeviceForm> getChildDeviceFormMap() {
        return childDeviceFormMap;
    }

    public void setChildDeviceFormMap(Map<String, DeviceForm> childDeviceFormMap) {
        this.childDeviceFormMap = childDeviceFormMap;
    }

    public Map<String, List<ProductPropertyForm>> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, List<ProductPropertyForm>> propertyMap) {
        this.propertyMap = propertyMap;
    }
}
