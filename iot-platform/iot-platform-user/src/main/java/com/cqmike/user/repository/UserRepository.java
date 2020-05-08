package com.cqmike.user.repository;

import com.cqmike.user.entity.User;
import com.cqmike.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: 
 * @Interface: UserRepository
 * @Description: UserRepository
 * @Author: chen qi
 * @Date: 2020-17-22 17:58
 * @Version: 1.0
**/
@Repository
public interface UserRepository extends BaseRepository<User, String> {
}