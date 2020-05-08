package com.cqmike.asset.controller;

import com.cqmike.asset.service.RuleService;
import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.common.platform.form.RuleForm;
import com.cqmike.common.platform.form.search.RuleSearchForm;
import com.cqmike.core.controller.BaseController;
import com.cqmike.core.result.ReturnForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @program: 
 * @Interface: RuleController
 * @Description: RuleController控制层类
 * @Author: chen qi
 * @Date: 2020-21-02 21:18
 * @Version: 1.0
**/
@RestController
@Api(tags = {"规则接口"})
public class RuleController extends BaseController {

    private final RuleService service;

    public RuleController(RuleService service) {
        this.service = service;
    }
    
    @ApiOperation("findById")
    @GetMapping("/rule/{id}")
    public ReturnForm<RuleForm> findById(@NotNull @PathVariable("id") String id) {
        RuleForm result = service.findById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("removeById")
    @DeleteMapping("/rule/{id}")
    public ReturnForm<RuleForm> removeById(@NotNull @PathVariable("id") String id) {
        RuleForm result = service.removeById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("listAllBySearchForm")
    @GetMapping("/rules")
    public ReturnForm<?> listAllBySearchForm(RuleSearchForm searchForm) {
        Integer page = searchForm.getPage();
        Integer size = searchForm.getSize();
        if (page == null && size == null) {
            List<RuleForm> formList = service.listAllBySearchForm(searchForm);
            return ReturnForm.success(formList);
        }
        Page<RuleForm> formList = service.listAllBySearchFormPage(searchForm);
        return ReturnForm.success(formList);
    }

    @ApiOperation("create")
    @PostMapping("/rule")
    public ReturnForm<RuleForm> create(RuleForm form) {
        RuleForm result = service.create(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("update")
    @PutMapping("/rule")
    public ReturnForm<RuleForm> update(RuleForm form) {
        RuleForm result = service.update(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("remove")
    @DeleteMapping("/rule")
    public ReturnForm<RuleForm> remove(RuleForm form)    {
        service.remove(form);
        return ReturnForm.success();
    }

    /**
     *  前置机初始化规则
     *  k -> 产品id   v -> (k -> 规则id, v -> 规则)
     * @return
     */
    @ApiIgnore
    @GetMapping("/feign/findRuleFrontList")
    public ReturnForm<Map<String, Map<String, RuleFormForFront>>> findRuleFrontList() {
        Map<String, Map<String, RuleFormForFront>> ruleFrontList = this.service.findRuleFrontList();
        return ReturnForm.success(ruleFrontList);
    }

}