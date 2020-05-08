package com.cqmike.asset.repository;

import com.cqmike.asset.entity.Product;
import com.cqmike.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: iot
 * @Interface: ProductRepository
 * @Description: ProductRepository
 * @Author: chen qi
 * @Date: 2020/2/29 21:33
 * @Version: 1.0
 **/
@Repository
public interface ProductRepository extends BaseRepository<Product, String> {
}
