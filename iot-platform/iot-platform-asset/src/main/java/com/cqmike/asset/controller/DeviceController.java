package com.cqmike.asset.controller;

import com.cqmike.asset.service.DeviceService;
import com.cqmike.common.front.form.DeviceFormForFront;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.search.DeviceSearchForm;
import com.cqmike.core.controller.BaseController;
import com.cqmike.core.result.ReturnForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: 
 * @Interface: DeviceController
 * @Description: DeviceController控制层类
 * @Author: chen qi
 * @Date: 2020-18-01 18:21
 * @Version: 1.0
**/
@RestController
@Api(tags = {"设备接口"})
public class DeviceController extends BaseController {

    private final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }
    
    @ApiOperation("findById")
    @ApiParam(name = "asd")
    @GetMapping("/device/{id}")
    public ReturnForm<DeviceForm> findById(@NotNull @PathVariable("id") String id) {
        DeviceForm result = service.findById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("removeById")
    @DeleteMapping("/device/{id}")
    public ReturnForm<DeviceForm> removeById(@NotNull @PathVariable("id") String id) {
        DeviceForm result = service.removeById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("listAllBySearchForm")
    @GetMapping("/devices")
    public ReturnForm<?> listAllBySearchForm(DeviceSearchForm searchForm) {
        Integer page = searchForm.getPage();
        Integer size = searchForm.getSize();
        if (page == null && size == null) {
            List<DeviceForm> formList = service.listAllBySearchForm(searchForm);
            return ReturnForm.success(formList);
        }
        Page<DeviceForm> formList = service.listAllBySearchFormPage(searchForm);
        return ReturnForm.success(formList);
    }

    @ApiOperation("create")
    @PostMapping("/device")
    public ReturnForm<DeviceForm> create(DeviceForm form) {
        DeviceForm result = service.create(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("update")
    @PutMapping("/device")
    public ReturnForm<DeviceForm> update(DeviceForm form) {
        DeviceForm result = service.update(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("remove")
    @DeleteMapping("/device")
    public ReturnForm<DeviceForm> remove(DeviceForm form) {
        service.remove(form);
        return ReturnForm.success();
    }

    @ApiOperation("查找子设备列表 分页")
    @GetMapping("/child/devices")
    public ReturnForm<Page<DeviceForm>> findChildDeviceList(@NotNull String parentId, Integer page, Integer size) {
        Page<DeviceForm> childDeviceList = service.findChildDeviceList(parentId, page, size);
        return ReturnForm.success(childDeviceList);
    }

    /**
     *  根据sn组装前置机对象
     * @param sn
     * @return
     */
    @ApiIgnore
    @GetMapping("/feign/findDeviceForFrontBySn")
    public ReturnForm<DeviceFormForFront> findDeviceForFrontBySn(@NotNull String sn){
        DeviceFormForFront front = this.service.findDeviceForFrontBySn(sn);
        return ReturnForm.success(front);
    }

}