package com.cqmike.asset.repository;

import com.cqmike.asset.entity.Gateway;
import com.cqmike.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: 
 * @Interface: GatewayRepository
 * @Description: GatewayRepository
 * @Author: chen qi
 * @Date: 2020-16-07 16:22
 * @Version: 1.0
**/
@Repository
public interface GatewayRepository extends BaseRepository<Gateway, String> {
}