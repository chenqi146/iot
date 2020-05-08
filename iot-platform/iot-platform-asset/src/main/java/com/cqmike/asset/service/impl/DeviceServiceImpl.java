package com.cqmike.asset.service.impl;

import com.cqmike.asset.convert.DeviceConvert;
import com.cqmike.asset.entity.Device;
import com.cqmike.asset.repository.DeviceRepository;
import com.cqmike.asset.service.DeviceService;
import com.cqmike.asset.service.GatewayService;
import com.cqmike.asset.service.ProductPropertyService;
import com.cqmike.asset.service.ProductService;
import com.cqmike.common.front.form.DeviceFormForFront;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.GatewayForm;
import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.common.platform.form.search.DeviceSearchForm;
import com.cqmike.common.platform.form.search.GatewaySearchForm;
import com.cqmike.common.platform.form.search.ProductPropertySearchForm;
import com.cqmike.core.exception.BusinessException;
import com.cqmike.core.service.AbstractCurdService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: 
 * @Interface: DeviceServiceImpl
 * @Description: DeviceServiceImpl实现类
 * @Author: chen qi
 * @Date: 2020-17-01 17:12
 * @Version: 1.0
**/

@Service
public class DeviceServiceImpl extends AbstractCurdService<Device, String, DeviceSearchForm, DeviceForm> implements DeviceService {

    private final DeviceRepository repository;

    private final GatewayService gatewayService;
    private final ProductService productService;
    private final ProductPropertyService productPropertyService;

    private final DeviceConvert convert;

    public DeviceServiceImpl(DeviceRepository repository,
                             GatewayService gatewayService,
                             ProductService productService,
                             ProductPropertyService productPropertyService,
                             DeviceConvert convert) {
        super(repository, convert);
        this.repository = repository;
        this.gatewayService = gatewayService;
        this.productService = productService;
        this.productPropertyService = productPropertyService;
        this.convert = convert;
    }

    @Override
    public DeviceFormForFront findDeviceForFrontBySn(@NotNull String sn) {

        DeviceFormForFront front = new DeviceFormForFront();

        // 当前设备
        DeviceForm deviceForm = this.findOneBySn(sn);
        front.setCurrentDeviceForm(deviceForm);

        // 当前产品
        String productId = deviceForm.getProductId();
        ProductForm productForm = productService.findById(productId);
        front.setCurrentProductForm(productForm);

        // 产品的属性
        ProductPropertySearchForm searchForm = new ProductPropertySearchForm();
        searchForm.setProductId(productId);
        List<ProductPropertyForm> productPropertyForms = productPropertyService.listAllBySearchForm(searchForm);
        Map<String, List<ProductPropertyForm>> propertyMap = new HashMap<>(2);
        propertyMap.put(productId, productPropertyForms);
        front.setPropertyMap(propertyMap);

        // 网关设备
        ProductTypeEnum productType = productForm.getType();
        if (productType != ProductTypeEnum.GATEWAY) {
            return front;
        }

        GatewaySearchForm gatewaySearchForm = new GatewaySearchForm();
        gatewaySearchForm.setDeviceId(deviceForm.getId());
        List<GatewayForm> gatewayForms = gatewayService.listAllBySearchForm(gatewaySearchForm);

        List<String> deviceIdList = gatewayForms.stream().map(GatewayForm::getChildDeviceId).collect(Collectors.toList());

        // 所有的子设备
        DeviceSearchForm deviceSearchForm = new DeviceSearchForm();
        deviceSearchForm.setDeviceIdList(deviceIdList);
        List<DeviceForm> childDeviceFormList = this.listAllBySearchForm(deviceSearchForm);

        Map<String, DeviceForm> deviceFormMap = childDeviceFormList.stream()
                .collect(Collectors.toMap(DeviceForm::getSn, d -> d));

        front.setChildDeviceFormMap(deviceFormMap);
        return front;
    }

    @Override
    public DeviceForm findOneBySn(@NotNull String sn) {

        Device search = new Device();
        search.setSn(sn);
        Example<Device> deviceExample = Example.of(search);
        Optional<Device> one = repository.findOne(deviceExample);
        Device device = one.<BusinessException>orElseThrow(() -> {
            throw new BusinessException("没有找到" + sn + "对应的设备");
        });
        return convert.convertToForm(device);
    }

    @Override
    public Page<DeviceForm> findChildDeviceList(@NotNull String parentId, Integer page, Integer size) {
        // 找到子设备idList
        GatewaySearchForm gatewaySearchForm = new GatewaySearchForm();
        gatewaySearchForm.setDeviceId(parentId);
        gatewaySearchForm.setPage(page);
        gatewaySearchForm.setSize(size);
        Page<GatewayForm> gatewayForms = gatewayService.listAllBySearchFormPage(gatewaySearchForm);
        List<String> deviceIdList = gatewayForms.stream().map(GatewayForm::getChildDeviceId)
                .collect(Collectors.toList());

        // 找出所有设备
        DeviceSearchForm deviceSearchForm = new DeviceSearchForm();
        deviceSearchForm.setDeviceIdList(deviceIdList);
        deviceSearchForm.setPage(page);
        deviceSearchForm.setSize(size);
        Page<DeviceForm> deviceForms = this.listAllBySearchFormPage(deviceSearchForm);

        // 找出父设备和父产品的名称
        DeviceForm parentDevice = this.findById(parentId);
        String parentProductId = parentDevice.getProductId();
        ProductForm parentProduct = productService.findById(parentProductId);

        deviceForms.forEach(device -> {
            device.setParentProductName(parentProduct.getName());
            device.setParentDeviceName(parentDevice.getName());
        });

        return deviceForms;
    }
}