package com.cqmike.asset.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.cqmike.asset.convert.DeviceConvert;
import com.cqmike.asset.convert.ProductConvert;
import com.cqmike.asset.entity.Device;
import com.cqmike.asset.entity.Product;
import com.cqmike.asset.repository.DeviceRepository;
import com.cqmike.asset.repository.ProductRepository;
import com.cqmike.asset.service.GatewayService;
import com.cqmike.asset.service.ProductPropertyParserService;
import com.cqmike.asset.service.ProductPropertyService;
import com.cqmike.asset.service.ProductService;
import com.cqmike.common.dto.MockProductDTO;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.common.platform.form.*;
import com.cqmike.common.platform.form.search.DeviceSearchForm;
import com.cqmike.common.platform.form.search.ProductPropertyParserSearchForm;
import com.cqmike.common.platform.form.search.ProductPropertySearchForm;
import com.cqmike.common.platform.form.search.ProductSearchForm;
import com.cqmike.core.service.AbstractCurdService;
import com.cqmike.core.specification.EntitySpecification;
import com.google.common.collect.Lists;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

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
    private final DeviceConvert deviceConvert;

    private final DeviceRepository deviceRepository;

    private final ProductPropertyService productPropertyService;

    private final GatewayService gatewayService;
    private final ProductPropertyParserService parserService;

    public ProductServiceImpl(ProductRepository repository,
                              ProductConvert convert,
                              DeviceConvert deviceConvert,
                              DeviceRepository deviceRepository, ProductPropertyService productPropertyService,
                              GatewayService gatewayService,
                              ProductPropertyParserService parserService) {
        super(repository, convert);
        this.repository = repository;
        this.convert = convert;
        this.deviceConvert = deviceConvert;
        this.deviceRepository = deviceRepository;
        this.productPropertyService = productPropertyService;
        this.gatewayService = gatewayService;
        this.parserService = parserService;
    }

    /**
     *  获取mock数据
     * @return
     */
    @Override
    public List<MockProductDTO> findAllProductMockList() {

        List<MockProductDTO> dtoList = Lists.newArrayList();

        List<ProductForm> productForms = this.listAll();
        if (CollUtil.isEmpty(productForms)) {
            return Collections.emptyList();
        }

        // 避免循环依赖  从dao层拿数据
        List<Device> deviceList = deviceRepository.findAll();
        List<DeviceForm> deviceForms = deviceConvert.convertToFormList(deviceList);
        if (CollUtil.isEmpty(deviceForms)) {
            return Collections.emptyList();
        }

        // k -> productId  v-> deviceList
        Map<String, List<DeviceForm>> deviceMap = deviceForms.stream()
                .collect(Collectors.groupingBy(DeviceForm::getProductId));

        List<ProductPropertyForm> propertyFormList = productPropertyService.listAll();
        Map<String, List<ProductPropertyForm>> propMap = propertyFormList.stream()
                .collect(Collectors.groupingBy(ProductPropertyForm::getProductId));

        List<GatewayForm> gatewayForms = gatewayService.listAll();
        Map<String, String> gatewayMap = gatewayForms.stream().collect(
                Collectors.toMap(GatewayForm::getChildDeviceId, GatewayForm::getDeviceSn, (k1, k2) -> k1));

        // 组装数据
        MockProductDTO dto;
        for (ProductForm productForm : productForms) {
            dto = new MockProductDTO();
            BeanUtil.copyProperties(productForm, dto);
            String productId = productForm.getId();
            dto.setDeviceFormList(deviceMap.get(productId));
            List<ProductPropertyForm> productPropertyForms = propMap.get(productId);
            if (CollUtil.isNotEmpty(productPropertyForms)) {
                productPropertyForms.sort(Comparator.comparing(ProductPropertyForm::getCreateTime));
            }
            dto.setPropertyFormList(productPropertyForms);
            if (productForm.getType() == ProductTypeEnum.CHILD_DEVICE) {
                dto.setChildDeviceMapperMap(gatewayMap);
            }
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Nonnull
    @Override
    public ProductForm removeById(@Nonnull String id) {
        ProductPropertySearchForm searchForm = new ProductPropertySearchForm();
        searchForm.setProductId(id);
        Set<String> propIds = productPropertyService.listAllBySearchForm(searchForm).stream()
                .map(ProductPropertyForm::getId).collect(Collectors.toSet());

        // 没有监听批量方法  todo 设计错误
        if (CollUtil.isNotEmpty(propIds)) {
            for (String propId : propIds) {
                productPropertyService.removeById(propId);
            }
        }

        ProductPropertyParserSearchForm parserSearchForm = new ProductPropertyParserSearchForm();
        parserSearchForm.setProductId(id);
        Set<String> parserIds = parserService.listAllBySearchForm(parserSearchForm).stream()
                .map(ProductPropertyParserForm::getId).collect(Collectors.toSet());

        // 没有监听批量方法  todo 设计错误
        if (CollUtil.isNotEmpty(propIds)) {
            for (String parserId : parserIds) {
                parserService.removeById(parserId);
            }
        }

        DeviceSearchForm deviceSearchForm = new DeviceSearchForm();
        deviceSearchForm.setProductId(id);

        Device device = new Device();
        device.setProductId(id);
        Example<Device> example = Example.of(device);
        List<Device> deviceList = deviceRepository.findAll(example);
        if (CollUtil.isNotEmpty(deviceList)) {
            deviceRepository.deleteInBatch(deviceList);
        }
        return super.removeById(id);
    }

    @Override
    public Page<ProductForm> listAllBySearchFormPage(ProductSearchForm productSearchForm) {
        EntitySpecification<ProductSearchForm, Product> specification = new EntitySpecification(productSearchForm);
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(productSearchForm.getPage(), productSearchForm.getSize(), sort);
        Page<Product> all = this.repository.findAll(specification, pageable);
        return all.map(convert::convertToForm);
    }
}