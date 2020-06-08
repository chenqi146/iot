package com.cqmike.asset.service.impl;

import com.cqmike.asset.convert.ProductPropertyParserConvert;
import com.cqmike.asset.entity.ProductPropertyParser;
import com.cqmike.common.platform.form.ProductPropertyParserForm;
import com.cqmike.common.platform.form.search.ProductPropertyParserSearchForm;
import com.cqmike.asset.repository.ProductPropertyParserRepository;
import com.cqmike.asset.service.ProductPropertyParserService;
import com.cqmike.core.exception.BusinessException;
import com.cqmike.core.service.AbstractCurdService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @program: 
 * @Interface: ProductPropertyParserServiceImpl
 * @Description: ProductPropertyParserServiceImpl实现类
 * @Author: chen qi
 * @Date: 2020-17-01 17:24
 * @Version: 1.0
**/

@Service
public class ProductPropertyParserServiceImpl extends AbstractCurdService<ProductPropertyParser, String, ProductPropertyParserSearchForm, ProductPropertyParserForm> implements ProductPropertyParserService {

    private final ProductPropertyParserRepository repository;
    
    private final ProductPropertyParserConvert convert;

    public ProductPropertyParserServiceImpl(ProductPropertyParserRepository repository,
                                ProductPropertyParserConvert convert) {
        super(repository, convert);
        this.repository = repository;
        this.convert = convert;
    }

    @Override
    public void removeInBatch(@NonNull Collection<String> strings) {
        throw new BusinessException("不允许批量删除");

    }

    @Override
    public void removeAll(@NonNull Collection<ProductPropertyParserForm> forms) {
        throw new BusinessException("不允许批量删除");

    }

    @Override
    public ProductPropertyParserForm removeById(String s) {
        return super.removeById(s);
    }

    @Override
    public ProductPropertyParserForm create(ProductPropertyParserForm form) {
        return super.create(form);
    }

    @Override
    public List<ProductPropertyParserForm> createInBatch(Collection<ProductPropertyParserForm> forms) {
        return super.createInBatch(forms);
    }

    @Override
    public ProductPropertyParserForm update(ProductPropertyParserForm form) {
        return super.update(form);
    }

    @Override
    public List<ProductPropertyParserForm> updateInBatch(Collection<ProductPropertyParserForm> forms) {
        return super.updateInBatch(forms);
    }
}