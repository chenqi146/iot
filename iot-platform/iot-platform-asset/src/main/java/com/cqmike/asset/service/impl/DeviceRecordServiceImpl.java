package com.cqmike.asset.service.impl;

import com.cqmike.asset.convert.DeviceRecordConvert;
import com.cqmike.asset.entity.DeviceRecord;
import com.cqmike.asset.repository.DeviceRecordRepository;
import com.cqmike.asset.service.DeviceRecordService;
import com.cqmike.common.platform.form.DeviceRecordForm;
import com.cqmike.common.platform.form.search.DeviceRecordSearchForm;
import com.cqmike.core.service.AbstractCurdService;
import com.cqmike.core.specification.EntitySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @program: 
 * @Interface: DeviceRecordServiceImpl
 * @Description: DeviceRecordServiceImpl实现类
 * @Author: chen qi
 * @Date: 2020-17-01 17:22
 * @Version: 1.0
**/

@Service
public class DeviceRecordServiceImpl extends AbstractCurdService<DeviceRecord, String, DeviceRecordSearchForm, DeviceRecordForm> implements DeviceRecordService {

    private final DeviceRecordRepository repository;
    
    private final DeviceRecordConvert convert;

    public DeviceRecordServiceImpl(DeviceRecordRepository repository,
                                DeviceRecordConvert convert) {
        super(repository, convert);
        this.repository = repository;
        this.convert = convert;
    }

    /**
     *  重写排序  基础框架没有封装排序
     * @param deviceRecordSearchForm
     * @return
     */
    @Override
    public List<DeviceRecordForm> listAllBySearchForm(DeviceRecordSearchForm deviceRecordSearchForm) {
        EntitySpecification<DeviceRecordSearchForm, DeviceRecord> specification = new EntitySpecification<>(deviceRecordSearchForm);
        Sort sort = Sort.by(Sort.Direction.DESC, "receiveTime");
        List<DeviceRecord> all = this.repository.findAll(specification, sort);
        return CollectionUtils.isEmpty(all) ? Collections.emptyList() : this.convert.convertToFormList(all);
    }

    @Override
    public Page<DeviceRecordForm> listAllBySearchFormPage(DeviceRecordSearchForm deviceRecordSearchForm) {
        EntitySpecification<DeviceRecordSearchForm, DeviceRecord> specification = new EntitySpecification<>(deviceRecordSearchForm);
        Sort sort = Sort.by(Sort.Direction.DESC, "receiveTime");
        Pageable pageable = PageRequest.of(deviceRecordSearchForm.getPage(), deviceRecordSearchForm.getSize(), sort);
        Page<DeviceRecord> all = this.repository.findAll(specification, pageable);
        return all.map(convert::convertToForm);
    }
}