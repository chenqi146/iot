package com.cqmike.asset.repository;

import com.cqmike.asset.entity.DeviceRecord;
import com.cqmike.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: iot
 * @Interface: DeviceRecordRepository
 * @Description: DeviceRecordRepository
 * @Author: chen qi
 * @Date: 2020/2/29 21:39
 * @Version: 1.0
 **/
@Repository
public interface DeviceRecordRepository extends BaseRepository<DeviceRecord, String> {
}
