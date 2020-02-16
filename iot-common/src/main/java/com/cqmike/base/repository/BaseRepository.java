package com.cqmike.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @program: iot
 * @Description BaseRepository基类仓库   公共repository 不创建spring实例 @NoRepositoryBean
 * @Author 陈琪
 * @Date 2020-2-16 0016 22:38
 * @Version 1.0
 **/
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {



}
