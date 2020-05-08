package com.cqmike.asset.controller;

import com.cqmike.asset.service.DeviceRecordService;
import com.cqmike.common.platform.form.DeviceRecordForm;
import com.cqmike.common.platform.form.search.DeviceRecordSearchForm;
import com.cqmike.core.controller.BaseController;
import com.cqmike.core.result.ReturnForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: 
 * @Interface: DeviceRecordController
 * @Description: DeviceRecordController控制层类
 * @Author: chen qi
 * @Date: 2020-21-02 21:16
 * @Version: 1.0
**/
@RestController
@Api(tags = {"设备数据接口"})
public class DeviceRecordController extends BaseController {

    private final DeviceRecordService service;

    public DeviceRecordController(DeviceRecordService service) {
        this.service = service;
    }
    
    @ApiOperation("findById")
    @GetMapping("/deviceRecord/{id}")
    public ReturnForm<DeviceRecordForm> findById(@NotNull @PathVariable("id") String id) {
        DeviceRecordForm result = service.findById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("removeById")
    @DeleteMapping("/deviceRecord/{id}")
    public ReturnForm<DeviceRecordForm> removeById(@NotNull @PathVariable("id") String id) {
        DeviceRecordForm result = service.removeById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("listAllBySearchForm")
    @GetMapping("/deviceRecords")
    public ReturnForm<?> listAllBySearchForm(DeviceRecordSearchForm searchForm) {
        Integer page = searchForm.getPage();
        Integer size = searchForm.getSize();
        if (page == null && size == null) {
            List<DeviceRecordForm> formList = service.listAllBySearchForm(searchForm);
            return ReturnForm.success(formList);
        }
        Page<DeviceRecordForm> formList = service.listAllBySearchFormPage(searchForm);
        return ReturnForm.success(formList);
    }

    @ApiOperation("create")
    @PostMapping("/deviceRecord")
    public ReturnForm<DeviceRecordForm> create(DeviceRecordForm form) {
        DeviceRecordForm result = service.create(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("update")
    @PutMapping("/deviceRecord")
    public ReturnForm<DeviceRecordForm> update(DeviceRecordForm form) {
        DeviceRecordForm result = service.update(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("remove")
    @DeleteMapping("/deviceRecord")
    public ReturnForm<DeviceRecordForm> remove(DeviceRecordForm form) {
        service.remove(form);
        return ReturnForm.success();
    }

}