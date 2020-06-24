package com.cqmike.asset.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
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
import com.cqmike.core.exception.BusinessException;
import com.cqmike.core.service.AbstractCurdService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
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

    /**
     *  根据sn组装前置机对象
     * @param sn
     * @return
     */
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
        List<ProductPropertyForm> productPropertyForms = productPropertyService.listAll();
        Map<String, List<ProductPropertyForm>> propertyMap = productPropertyForms.stream()
                .collect(Collectors.groupingBy(ProductPropertyForm::getProductId));
        front.setPropertyMap(propertyMap);

        // 网关设备
        ProductTypeEnum productType = productForm.getType();
        if (productType != ProductTypeEnum.GATEWAY) {
            return front;
        }

        GatewaySearchForm gatewaySearchForm = new GatewaySearchForm();
        gatewaySearchForm.setDeviceId(deviceForm.getId());
        List<GatewayForm> gatewayForms = gatewayService.listAllBySearchForm(gatewaySearchForm);

        List<String> deviceIdList = gatewayForms.stream()
                .map(GatewayForm::getChildDeviceId).collect(Collectors.toList());

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

    /**
     *  通过 设备id查找子设备
     * @param parentId 设备id
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<DeviceForm> findChildDeviceList(@NotNull String parentId, Integer page, Integer size) {
        // 找到子设备idList
        GatewaySearchForm gatewaySearchForm = new GatewaySearchForm();
        gatewaySearchForm.setDeviceId(parentId);
        List<GatewayForm> gatewayForms = gatewayService.listAllBySearchForm(gatewaySearchForm);
        List<String> deviceIdList = gatewayForms.stream().map(GatewayForm::getChildDeviceId)
                .collect(Collectors.toList());

        if (CollUtil.isEmpty(deviceIdList)) {
            return Page.empty();
        }

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

    @NonNull
    @Override
    public DeviceForm create(@NonNull DeviceForm form) {

        String parentDeviceId = form.getParentDeviceId();
        String productId = form.getProductId();
        ProductForm productForm = productService.findById(productId);

        form.setProductName(productForm.getName());

        DeviceForm deviceForm = super.create(form);
        if (StrUtil.isEmpty(parentDeviceId)) {
            return deviceForm;
        }

        // 网关子设备 携带 parentDeviceId  网关表添加
        DeviceForm parent = this.findById(parentDeviceId);

        GatewayForm gatewayForm = new GatewayForm();
        gatewayForm.setDeviceId(parent.getId());
        gatewayForm.setDeviceSn(parent.getSn());
        gatewayForm.setChildDeviceId(deviceForm.getId());
        gatewayForm.setChildDeviceSn(deviceForm.getSn());
        gatewayService.create(gatewayForm);

        return deviceForm;
    }

    @NonNull
    @Override
    public DeviceForm update(@NonNull DeviceForm form) {

        String id = form.getId();
        DeviceForm source = this.findById(id);

        this.checkFormUpdate(form, source);

        return super.update(source);
    }

    /**
     *  检查修改的参数， 可选性更新
     * @param form
     * @param source
     */
    private void checkFormUpdate(DeviceForm form, DeviceForm source) {

        String name = form.getName();
        if (StrUtil.isNotEmpty(name)) {
            source.setName(name);
        }

        Date installationDate = form.getInstallationDate();
        if (installationDate != null) {
            source.setInstallationDate(installationDate);
        }

        String installationLocation = form.getInstallationLocation();
        if (StrUtil.isNotEmpty(installationLocation)) {
            source.setInstallationLocation(installationLocation);
        }

        String productId = form.getProductId();
        if (StrUtil.isNotEmpty(productId)) {
            source.setProductId(productId);
        }

        String productName = form.getProductName();
        if (StrUtil.isNotEmpty(productName)) {
            source.setProductName(productName);
        }

        String parentDeviceId = form.getParentDeviceId();
        if (StrUtil.isNotEmpty(parentDeviceId)) {
            // 找到网关  该设备id所属的网关列表   一个设备只会属于一个网关设备或者没有
            GatewaySearchForm searchForm = new GatewaySearchForm();
            String deviceId = form.getId();
            searchForm.setChildDeviceId(deviceId);
            List<GatewayForm> gatewayForms = gatewayService.listAllBySearchForm(searchForm);
            Set<String> gatewayDeviceIds = gatewayForms.stream()
                    .map(GatewayForm::getDeviceId).collect(Collectors.toSet());

            if (!gatewayDeviceIds.contains(parentDeviceId)) {
                return;
            }

            // 删除所属的网关
            gatewayForms.forEach(g -> {
                if (!g.getDeviceId().equals(parentDeviceId)) {
                    return;
                }
                if (!g.getChildDeviceId().equals(deviceId)) {
                    return;
                }
                gatewayService.removeById(g.getId());
            });

            DeviceForm parentDevice = this.findById(parentDeviceId);
            GatewayForm gatewayForm = new GatewayForm();
            gatewayForm.setDeviceId(parentDeviceId);
            gatewayForm.setDeviceSn(parentDevice.getSn());
            gatewayForm.setChildDeviceId(deviceId);
            gatewayForm.setChildDeviceSn(form.getSn());

            gatewayService.create(gatewayForm);

        }

    }

    @Override
    public DeviceForm innerUpdate(DeviceForm form) {
        return super.update(form);
    }

    @NonNull
    @Override
    public DeviceForm findById(@NonNull String s) {
        DeviceForm form = super.findById(s);

        ProductTypeEnum type = form.getType();
        if (type == ProductTypeEnum.CHILD_DEVICE) {
            GatewaySearchForm searchForm = new GatewaySearchForm();
            searchForm.setChildDeviceId(form.getId());
            List<GatewayForm> gatewayForms = gatewayService.listAllBySearchForm(searchForm);
            if (CollUtil.isNotEmpty(gatewayForms)) {
                GatewayForm gatewayForm = gatewayForms.get(0);
                form.setParentDeviceId(gatewayForm.getDeviceId());
            }
        }

        return form;
    }
}