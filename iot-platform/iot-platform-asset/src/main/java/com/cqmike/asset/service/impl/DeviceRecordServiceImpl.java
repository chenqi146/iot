package com.cqmike.asset.service.impl;

import com.cqmike.common.platform.form.DeviceRecordForm;
import com.cqmike.asset.convert.DeviceRecordConvert;
import com.cqmike.asset.entity.DeviceRecord;
import com.cqmike.common.platform.form.search.DeviceRecordSearchForm;
import com.cqmike.asset.repository.DeviceRecordRepository;
import com.cqmike.asset.service.DeviceRecordService;
import org.springframework.stereotype.Service;
import com.cqmike.core.service.AbstractCurdService;
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
}