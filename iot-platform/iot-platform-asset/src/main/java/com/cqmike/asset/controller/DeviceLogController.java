package com.cqmike.asset.controller;

import com.cqmike.asset.service.DeviceLogService;
import com.cqmike.common.platform.form.DeviceLogForm;
import com.cqmike.common.platform.form.search.DeviceLogSearchForm;
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
 * @Interface: DeviceLogController
 * @Description: DeviceLogController控制层类
 * @Author: chen qi
 * @Date: 2020-21-02 21:15
 * @Version: 1.0
**/
@RestController
@Api(tags = {"设备日志接口"})
public class DeviceLogController extends BaseController {

    private final DeviceLogService service;

    public DeviceLogController(DeviceLogService service) {
        this.service = service;
    }
    
    @ApiOperation("findById")
    @GetMapping("/deviceLog/{id}")
    public ReturnForm<DeviceLogForm> findById(@NotNull @PathVariable("id") String id) {
        DeviceLogForm result = service.findById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("removeById")
    @DeleteMapping("/deviceLog/{id}")
    public ReturnForm<DeviceLogForm> removeById(@NotNull @PathVariable("id") String id) {
        DeviceLogForm result = service.removeById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("listAllBySearchForm")
    @GetMapping("/deviceLogs")
    public ReturnForm<?> listAllBySearchForm(DeviceLogSearchForm searchForm) {
        Integer page = searchForm.getPage();
        Integer size = searchForm.getSize();
        if (page == null && size == null) {
            List<DeviceLogForm> formList = service.listAllBySearchForm(searchForm);
            return ReturnForm.success(formList);
        }
        Page<DeviceLogForm> formList = service.listAllBySearchFormPage(searchForm);
        return ReturnForm.success(formList);
    }

    @ApiOperation("create")
    @PostMapping("/deviceLog")
    public ReturnForm<DeviceLogForm> create(DeviceLogForm form) {
        DeviceLogForm result = service.create(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("update")
    @PutMapping("/deviceLog")
    public ReturnForm<DeviceLogForm> update(DeviceLogForm form) {
        DeviceLogForm result = service.update(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("remove")
    @DeleteMapping("/deviceLog")
    public ReturnForm<DeviceLogForm> remove(DeviceLogForm form) {
        service.remove(form);
        return ReturnForm.success();
    }

}