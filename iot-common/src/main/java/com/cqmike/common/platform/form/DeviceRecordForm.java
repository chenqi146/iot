package com.cqmike.common.platform.form;

import com.cqmike.core.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @program: 
 * @Interface: DeviceRecordForm
 * @Description: DeviceRecordForm表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:20
 * @Version: 1.0
**/
@ApiModel(value = "DeviceRecordForm")
public class DeviceRecordForm extends BaseForm {

    private static final Logger log = LoggerFactory.getLogger(DeviceRecordForm.class);

    /**
     * 设备sn
     */
    @ApiModelProperty("设备sn")
    private String deviceSn;

    /**
     * 产品id
     */
    @ApiModelProperty("产品id")
    private String productId;

    /**
     * 设备数据
     */
    @ApiModelProperty("设备数据")
    private String data;

    /**
     * 设备数据
     */
    @ApiModelProperty("设备数据json")
    private JsonNode dataJson;

    /**
     * 数据接收时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("数据接收时间")
    private Date receiveTime;

    public DeviceRecordForm() {
    }

    public void setData(String data) {
        this.data = data;
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.dataJson = mapper.readTree(data);
        } catch (JsonProcessingException e) {
            log.error("json格式化错误: ", e);
        }
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

    public String getData() {
        return data;
    }

    public JsonNode getDataJson() {
        return dataJson;
    }

    public void setDataJson(JsonNode dataJson) {
        this.dataJson = dataJson;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}