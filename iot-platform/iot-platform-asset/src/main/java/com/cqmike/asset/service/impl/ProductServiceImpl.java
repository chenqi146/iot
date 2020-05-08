package com.cqmike.asset.service.impl;

import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.asset.convert.ProductConvert;
import com.cqmike.asset.entity.Product;
import com.cqmike.common.platform.form.search.ProductSearchForm;
import com.cqmike.asset.repository.ProductRepository;
import com.cqmike.asset.service.ProductService;
import org.springframework.stereotype.Service;
import com.cqmike.core.service.AbstractCurdService;
/**
 * @program: 
 * @Interface: ProductServiceImpl
 * @Description: ProductServiceImpl实现类
 * @Author: chen qi
 * @Date: 2020-17-01 17:24
 * @Version: 1.0
**/

@Service
public class ProductServiceImpl extends AbstractCurdService<Product, String, ProductSearchForm, ProductForm> implements ProductService {

    private final ProductRepository repository;
    
    private final ProductConvert convert;

    public ProductServiceImpl(ProductRepository repository,
                                ProductConvert convert) {
        super(repository, convert);
        this.repository = repository;
        this.convert = convert;
    }
}