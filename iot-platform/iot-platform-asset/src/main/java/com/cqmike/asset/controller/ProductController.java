package com.cqmike.asset.controller;

import com.cqmike.asset.service.ProductService;
import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.common.platform.form.search.ProductSearchForm;
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
 * @Interface: ProductController
 * @Description: ProductController控制层类
 * @Author: chen qi
 * @Date: 2020-21-02 21:17
 * @Version: 1.0
**/
@RestController
@Api(tags = {"产品接口"})
public class ProductController extends BaseController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    
    @ApiOperation("findById")
    @GetMapping("/product/{id}")
    public ReturnForm<ProductForm> findById(@NotNull @PathVariable("id") String id) {
        ProductForm result = service.findById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("removeById")
    @DeleteMapping("/product/{id}")
    public ReturnForm<ProductForm> removeById(@NotNull @PathVariable("id") String id) {
        ProductForm result = service.removeById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("listAllBySearchForm")
    @GetMapping("/products")
    public ReturnForm<?> listAllBySearchForm(ProductSearchForm searchForm) {
        Integer page = searchForm.getPage();
        Integer size = searchForm.getSize();
        if (page == null && size == null) {
            List<ProductForm> formList = service.listAllBySearchForm(searchForm);
            return ReturnForm.success(formList);
        }
        Page<ProductForm> formList = service.listAllBySearchFormPage(searchForm);
        return ReturnForm.success(formList);
    }

    @ApiOperation("create")
    @PostMapping("/product")
    public ReturnForm<ProductForm> create(ProductForm form) {
        ProductForm result = service.create(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("update")
    @PutMapping("/product")
    public ReturnForm<ProductForm> update(ProductForm form) {
        ProductForm result = service.update(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("remove")
    @DeleteMapping("/product")
    public ReturnForm<ProductForm> remove(ProductForm form) {
        service.remove(form);
        return ReturnForm.success();
    }

}