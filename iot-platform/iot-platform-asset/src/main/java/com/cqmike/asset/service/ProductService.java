package com.cqmike.asset.service;
import com.cqmike.common.dto.MockProductDTO;
import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.common.platform.form.search.ProductSearchForm;
import com.cqmike.core.service.CurdService;

import java.util.List;

/**
 * @program: 
 * @Interface: ProductService
 * @Description: ProductService接口类
 * @Author: chen qi
 * @Date: 2020-17-01 17:34
 * @Version: 1.0
**/
public interface ProductService extends CurdService<ProductForm, String, ProductSearchForm> {

    /**
     *  组装mock数据
     * @return
     */
    List<MockProductDTO> findAllProductMockList();
}