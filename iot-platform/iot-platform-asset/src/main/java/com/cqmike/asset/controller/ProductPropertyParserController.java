package com.cqmike.asset.controller;

import cn.hutool.core.util.StrUtil;
import com.cqmike.asset.service.ProductPropertyParserService;
import com.cqmike.common.platform.form.ProductPropertyParserForm;
import com.cqmike.common.platform.form.search.ProductPropertyParserSearchForm;
import com.cqmike.core.controller.BaseController;
import com.cqmike.core.result.ReturnForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program:
 * @Interface: ProductPropertyParserController
 * @Description: ProductPropertyParserController控制层类
 * @Author: chen qi
 * @Date: 2020-21-02 21:18
 * @Version: 1.0
 **/
@RestController
@Api(tags = {"产品属性解析类接口"})
public class ProductPropertyParserController extends BaseController {

    private final ProductPropertyParserService service;

    public ProductPropertyParserController(ProductPropertyParserService service) {
        this.service = service;
    }

    @ApiOperation("findById")
    @GetMapping("/productPropertyParser/{id}")
    public ReturnForm<ProductPropertyParserForm> findById(@NotNull @PathVariable("id") String id) {
        ProductPropertyParserForm result = service.findById(id);
        String script = result.getScript();
        int function = StrUtil.indexOf(script, "function", 0, true);
        String sub = StrUtil.sub(script, function, script.length());
        result.setScript(sub);
        return ReturnForm.success(result);
    }

    @ApiOperation("removeById")
    @DeleteMapping("/productPropertyParser/{id}")
    public ReturnForm<ProductPropertyParserForm> removeById(@NotNull @PathVariable("id") String id) {
        ProductPropertyParserForm result = service.removeById(id);
        return ReturnForm.success(result);
    }

    @ApiOperation("listAllBySearchForm")
    @GetMapping("/productPropertyParsers")
    public ReturnForm<?> listAllBySearchForm(ProductPropertyParserSearchForm searchForm) {
        Integer page = searchForm.getPage();
        Integer size = searchForm.getSize();
        if (page == null && size == null) {
            List<ProductPropertyParserForm> formList = service.listAllBySearchForm(searchForm);
            formList.forEach(f -> {
                String script = f.getScript();
                int function = StrUtil.indexOf(script, "function", 0, true);
                String sub = StrUtil.sub(script, function, script.length());
                f.setScript(sub);
            });
            return ReturnForm.success(formList);
        }
        Page<ProductPropertyParserForm> formList = service.listAllBySearchFormPage(searchForm);
        List<ProductPropertyParserForm> content = formList.getContent();
        content.forEach(f -> {
            String script = f.getScript();
            int function = StrUtil.indexOf(script, "function", 0, true);
            String sub = StrUtil.sub(script, function, script.length());
            f.setScript(sub);
        });
        return ReturnForm.success(formList);
    }

    @ApiOperation("create")
    @PostMapping("/productPropertyParser")
    public ReturnForm<ProductPropertyParserForm> create(ProductPropertyParserForm form) {
        String script = form.getScript();
        String readScript = "var dataScript" + form.getProductId() + "dataScript = " + script;
        form.setScript(readScript);
        ProductPropertyParserForm result = service.create(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("update")
    @PutMapping("/productPropertyParser")
    public ReturnForm<ProductPropertyParserForm> update(ProductPropertyParserForm form) {
        String script = form.getScript();
        String readScript = "var dataScript" + form.getProductId() + "dataScript = " + script;
        form.setScript(readScript);
        ProductPropertyParserForm result = service.update(form);
        return ReturnForm.success(result);
    }

    @ApiOperation("remove")
    @DeleteMapping("/productPropertyParser")
    public ReturnForm<ProductPropertyParserForm> remove(ProductPropertyParserForm form) {
        service.remove(form);
        return ReturnForm.success();
    }

    @ApiIgnore
    @GetMapping("/feign/listAll")
    public ReturnForm<List<ProductPropertyParserForm>> listAll() {
        List<ProductPropertyParserForm> forms = service.listAll();
        return ReturnForm.success(forms);
    }

    public static void main(String[] args) {
        String temp = "var dataScript = function(value) {\n" +
                "\tvar scriptUtil = Java.type('com.cqmike.front.util.ByteBufUtil');\n" +
                "\tvar Voltage = scriptUtil.readDouble(value);\n" +
                "   var ElectricCurrent = scriptUtil.readDouble(value);\n" +
                "   var result = {\n" +
                "   \t  'Voltage': Voltage,\n" +
                "\t \t'ElectricCurrent': ElectricCurrent\n" +
                "   };\n" +
                "  \n" +
                "  return JSON.stringify(result);\n" +
                "}\t";

        int function = StrUtil.indexOf(temp, "function", 0, true);
        System.out.println(StrUtil.sub(temp, function, temp.length()));
    }
}