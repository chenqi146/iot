package com.cqmike.front.client;

import com.cqmike.common.front.form.DeviceFormForFront;
import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.common.platform.form.ProductPropertyParserForm;
import com.cqmike.core.result.ReturnForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @program: iot
 * @Interface: PlatformClient
 * @Description: 调用平台接口
 * @Author: chen qi
 * @Date: 2020/3/29 11:09
 * @Version: 1.0
 **/
@FeignClient(name = "${feign.platform.url}", url = "${feign.platform.url}")
public interface PlatformClient {

    /**
     *  前置机 拉取平台设备信息接口
     * @param deviceSn
     * @return
     */
    @GetMapping("/feign/findDeviceForFrontBySn")
    ReturnForm<DeviceFormForFront> findDeviceForFrontBySn(String deviceSn);


    /**
     *  查询所有
     * @return
     */
    @GetMapping("/feign/listAll")
    ReturnForm<List<ProductPropertyParserForm>> listAll();

    /**
     *  查询所有
     * @return
     */
    @GetMapping("/feign/listAll")
    ReturnForm<Map<String, Map<String,ProductPropertyForm>>> findPropertyList();


    /**
     * 前置机初始化规则
     * k -> 产品id   v -> (k -> 规则id, v -> 规则)
     *
     * @return
     */
    @GetMapping("/feign/findRuleFrontList")
    ReturnForm<Map<String, Map<String, RuleFormForFront>>> findRuleFrontList();

}
