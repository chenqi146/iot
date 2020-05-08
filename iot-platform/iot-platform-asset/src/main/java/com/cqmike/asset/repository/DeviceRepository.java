package com.cqmike.asset.repository;

import com.cqmike.asset.entity.Device;
import com.cqmike.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: 
 * @Interface: DeviceRepository
 * @Description: DeviceRepository
 * @Author: chen qi
 * @Date: 2020-16-01 16:04
 * @Version: 1.0
**/
@Repository
public interface DeviceRepository extends BaseRepository<Device, String> {

}