package com.cqmike.mock.client;

import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.core.result.ReturnForm;
import com.cqmike.common.dto.MockProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @program: iot
 * @Interface: PlatformClient
 * @Description: mock 调用平台接口
 * @Author: chen qi
 * @Date: 2020/4/16 18:41
 * @Version: 1.0
 **/
@FeignClient(name = "${feign.platform.url}", url = "${feign.platform.url}")
public interface PlatformClient {

    /**
     * 从平台获取mock端用于模拟的数据对象  todo 平台端实现了  待测试
     * @return
     */
    @GetMapping("/feign/mock/findAllProductMockList")
    ReturnForm<List<MockProductDTO>> findAllProductMockList();

    /**
     *  获取所有设备列表
     * @return
     */
    @GetMapping("/feign/mock/devices")
    ReturnForm<List<DeviceForm>> findAllDeviceList();

}
