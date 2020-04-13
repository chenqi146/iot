package com.cqmike.common.platform.form;
import com.cqmike.core.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: 
 * @Interface: DeviceLogForm
 * @Description: DeviceLogForm表单类
 * @Author: chen qi
 * @Date: 2020-16-01 16:11
 * @Version: 1.0
**/
@ApiModel(value = "DeviceLogForm")
public class DeviceLogForm extends BaseForm {
    /**
     * 设备id
     */
    @ApiModelProperty("设备id")
    private String deviceId;

    /**
     * 操作类型  增删改
     */
    @ApiModelProperty("操作类型  增删改")
    private String type;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    public DeviceLogForm() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}