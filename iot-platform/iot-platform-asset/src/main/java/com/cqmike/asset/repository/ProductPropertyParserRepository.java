package com.cqmike.asset.repository;

import com.cqmike.asset.entity.ProductPropertyParser;
import com.cqmike.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: iot
 * @Interface: ProductPropertyParserRepository
 * @Description: ProductPropertyParserRepository
 * @Author: chen qi
 * @Date: 2020/2/29 21:37
 * @Version: 1.0
 **/
@Repository
public interface ProductPropertyParserRepository extends BaseRepository<ProductPropertyParser, String> {
}
