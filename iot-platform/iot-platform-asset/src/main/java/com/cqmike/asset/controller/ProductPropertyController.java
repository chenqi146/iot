package com.cqmike.asset.controller;

import com.cqmike.asset.service.ProductPropertyService;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.common.platform.form.search.ProductPropertySearchForm;
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
 * @Interface: ProductPropertyController
 * @Description: ProductPropertyController控制层类
 * @Author: chen qi
 * @Date: 2020-21-02 21:17
 * @Version: 1.0
**/
@RestController
@Api(tags = {"产品属性接口"})
public class ProductPropertyController extends BaseController {

    private final ProductPropertyService service;

    public ProductPropertyController(ProductPropertyService service) {
        this.service = service;
    }
    
    @ApiOperation("findById")
    @GetMapping("/productProperty/{id}")
    public ReturnForm<ProductPropertyForm> findById(@NotNull @PathVariable("id") String id) {
        ProductPropertyForm result = service.findById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("removeById")
    @DeleteMapping("/productProperty/{id}")
    public ReturnForm<ProductPropertyForm> removeById(@NotNull @PathVariable("id") String id) {
        ProductPropertyForm result = service.removeById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("listAllBySearchForm")
    @GetMapping("/productProperties")
    public ReturnForm<?> listAllBySearchForm(ProductPropertySearchForm searchForm) {
        Integer page = searchForm.getPage();
        Integer size = searchForm.getSize();
        if (page == null && size == null) {
            List<ProductPropertyForm> formList = service.listAllBySearchForm(searchForm);
            return ReturnForm.success(formList);
        }
        Page<ProductPropertyForm> formList = service.listAllBySearchFormPage(searchForm);
        return ReturnForm.success(formList);
    }

    @ApiOperation("create")
    @PostMapping("/productProperty")
    public ReturnForm<ProductPropertyForm> create(ProductPropertyForm form) {
        ProductPropertyForm result = service.create(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("update")
    @PutMapping("/productProperty")
    public ReturnForm<ProductPropertyForm> update(ProductPropertyForm form) {
        ProductPropertyForm result = service.update(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("remove")
    @DeleteMapping("/productProperty")
    public ReturnForm<ProductPropertyForm> remove(ProductPropertyForm form) {
        service.remove(form);
        return ReturnForm.success();
    }

}