package com.cqmike.asset.repository;

import com.cqmike.asset.entity.DeviceLog;
import com.cqmike.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: iot
 * @Interface: DeviceLogRepository
 * @Description: DeviceLogRepository
 * @Author: chen qi
 * @Date: 2020/2/29 21:38
 * @Version: 1.0
 **/
@Repository
public interface DeviceLogRepository extends BaseRepository<DeviceLog, String> {
}
