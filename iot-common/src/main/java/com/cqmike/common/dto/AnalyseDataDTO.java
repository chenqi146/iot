package com.cqmike.common.dto;

/**
 * @program: iot
 * @ClassName: AnalyseDataDTO
 * @Description: 数据传输对象
 * @Author: chen qi
 * @Date: 2020/3/10 20:57
 * @Version: 1.0
 **/
public class AnalyseDataDTO {

    private String deviceSn;
    private String productId;

    private Object data;

    public AnalyseDataDTO() {
    }

    public AnalyseDataDTO(String deviceSn, String productId, Object data) {
        this.deviceSn = deviceSn;
        this.productId = productId;
        this.data = data;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
