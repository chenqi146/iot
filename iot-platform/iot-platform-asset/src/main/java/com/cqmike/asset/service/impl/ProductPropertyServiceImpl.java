package com.cqmike.asset.service.impl;

import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.asset.convert.ProductPropertyConvert;
import com.cqmike.asset.entity.ProductProperty;
import com.cqmike.common.platform.form.search.ProductPropertySearchForm;
import com.cqmike.asset.repository.ProductPropertyRepository;
import com.cqmike.asset.service.ProductPropertyService;
import org.springframework.stereotype.Service;
import com.cqmike.core.service.AbstractCurdService;

import java.util.Collection;
import java.util.List;

/**
 * @program: 
 * @Interface: ProductPropertyServiceImpl
 * @Description: ProductPropertyServiceImpl实现类
 * @Author: chen qi
 * @Date: 2020-17-01 17:24
 * @Version: 1.0
**/

@Service
public class ProductPropertyServiceImpl extends AbstractCurdService<ProductProperty, String, ProductPropertySearchForm, ProductPropertyForm> implements ProductPropertyService {

    private final ProductPropertyRepository repository;
    
    private final ProductPropertyConvert convert;

    public ProductPropertyServiceImpl(ProductPropertyRepository repository,
                                ProductPropertyConvert convert) {
        super(repository, convert);
        this.repository = repository;
        this.convert = convert;
    }

    @Override
    public ProductPropertyForm removeById(String s) {
        return super.removeById(s);
    }

    @Override
    public ProductPropertyForm create(ProductPropertyForm form) {
        return super.create(form);
    }

    @Override
    public List<ProductPropertyForm> createInBatch(Collection<ProductPropertyForm> forms) {
        return super.createInBatch(forms);
    }

    @Override
    public ProductPropertyForm update(ProductPropertyForm form) {
        return super.update(form);
    }

    @Override
    public List<ProductPropertyForm> updateInBatch(Collection<ProductPropertyForm> forms) {
        return super.updateInBatch(forms);
    }
}