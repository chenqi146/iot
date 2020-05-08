package com.cqmike.asset.service.impl;

import com.cqmike.common.platform.form.DeviceLogForm;
import com.cqmike.asset.convert.DeviceLogConvert;
import com.cqmike.asset.entity.DeviceLog;
import com.cqmike.common.platform.form.search.DeviceLogSearchForm;
import com.cqmike.asset.repository.DeviceLogRepository;
import com.cqmike.asset.service.DeviceLogService;
import org.springframework.stereotype.Service;
import com.cqmike.core.service.AbstractCurdService;
/**
 * @program: 
 * @Interface: DeviceLogServiceImpl
 * @Description: DeviceLogServiceImpl实现类
 * @Author: chen qi
 * @Date: 2020-17-01 17:22
 * @Version: 1.0
**/

@Service
public class DeviceLogServiceImpl extends AbstractCurdService<DeviceLog, String, DeviceLogSearchForm, DeviceLogForm> implements DeviceLogService {

    private final DeviceLogRepository repository;
    
    private final DeviceLogConvert convert;

    public DeviceLogServiceImpl(DeviceLogRepository repository,
                                DeviceLogConvert convert) {
        super(repository, convert);
        this.repository = repository;
        this.convert = convert;
    }
}