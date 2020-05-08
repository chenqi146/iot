package com.cqmike.asset.repository;

import com.cqmike.asset.entity.Rule;
import com.cqmike.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: iot
 * @Interface: RuleRepository
 * @Description: RuleRepository
 * @Author: chen qi
 * @Date: 2020/2/29 21:45
 * @Version: 1.0
 **/
@Repository
public interface RuleRepository extends BaseRepository<Rule, String> {
}
