package com.cqmike.asset.repository;

import com.cqmike.asset.entity.ProductProperty;
import com.cqmike.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: iot
 * @Interface: ProductPropertyRepository
 * @Description: ProductPropertyRepository
 * @Author: chen qi
 * @Date: 2020/2/29 21:36
 * @Version: 1.0
 **/
@Repository
public interface ProductPropertyRepository extends BaseRepository<ProductProperty, String> {
}
