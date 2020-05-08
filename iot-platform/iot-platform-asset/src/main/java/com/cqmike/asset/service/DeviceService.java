package com.cqmike.asset.service;

import com.cqmike.common.front.form.DeviceFormForFront;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.search.DeviceSearchForm;
import com.cqmike.core.service.CurdService;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;

/**
 * @program: 
 * @Interface: DeviceService
 * @Description: DeviceService接口类
 * @Author: chen qi
 * @Date: 2020-10-01 10:57
 * @Version: 1.0
**/
public interface DeviceService extends CurdService<DeviceForm, String, DeviceSearchForm> {

    /**
     *  根据sn组装前置机对象
     * @param sn
     * @return
     */
    DeviceFormForFront findDeviceForFrontBySn(@NotNull String sn);

    /**
     *  根据sn查询设备
     * @param sn
     * @return
     */
    DeviceForm findOneBySn(@NotNull String sn);

    /**
     *  通过 设备id查找子设备
     * @param parentId 设备id
     * @param page
     * @param size
     * @return
     */
    Page<DeviceForm> findChildDeviceList(@NotNull String parentId, Integer page, Integer size);
}