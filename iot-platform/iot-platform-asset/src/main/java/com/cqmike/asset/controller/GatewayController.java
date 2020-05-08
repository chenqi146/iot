package com.cqmike.asset.controller;

import com.cqmike.asset.service.GatewayService;
import com.cqmike.common.platform.form.GatewayForm;
import com.cqmike.common.platform.form.search.GatewaySearchForm;
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
 * @Interface: GatewayController
 * @Description: GatewayController控制层类
 * @Author: chen qi
 * @Date: 2020-16-07 16:22
 * @Version: 1.0
**/
@RestController
@Api(tags = {"网关接口"})
public class GatewayController extends BaseController {

    private final GatewayService service;

    public GatewayController(GatewayService service) {
        this.service = service;
    }
    
    @ApiOperation("findById")
    @GetMapping("/gateway/{id}")
    public ReturnForm<GatewayForm> findById(@NotNull @PathVariable("id") String id) {
        GatewayForm result = service.findById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("removeById")
    @DeleteMapping("/gateway/{id}")
    public ReturnForm<GatewayForm> removeById(@NotNull @PathVariable("id") String id) {
        GatewayForm result = service.removeById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("listAllBySearchForm")
    @GetMapping("/gateways")
    public ReturnForm<?> listAllBySearchForm(GatewaySearchForm searchForm) {
        Integer page = searchForm.getPage();
        Integer size = searchForm.getSize();
        if (page == null && size == null) {
            List<GatewayForm> formList = service.listAllBySearchForm(searchForm);
            return ReturnForm.success(formList);
        }
        Page<GatewayForm> formList = service.listAllBySearchFormPage(searchForm);
        return ReturnForm.success(formList);
    }

    @ApiOperation("create")
    @PostMapping("/gateway")
    public ReturnForm<GatewayForm> create(GatewayForm form) {
        GatewayForm result = service.create(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("update")
    @PutMapping("/gateway")
    public ReturnForm<GatewayForm> update(GatewayForm form) {
        GatewayForm result = service.update(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("remove")
    @DeleteMapping("/gateway")
    public ReturnForm<GatewayForm> remove(GatewayForm form) {
        service.remove(form);
        return ReturnForm.success();
    }

}