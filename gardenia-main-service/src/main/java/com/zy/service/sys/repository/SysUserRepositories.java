package com.zy.service.sys.repository;

import com.zy.service.sys.entity.SysUser;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @auther zy-栀
 * @create 2023-02-44 11:47:56
 * @describe 用户表服务类
 */
@Repository
public interface SysUserRepositories extends R2dbcRepository<SysUser, String> {

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Query("update user set name = :#{#user.name}, age = :#{#user.age} where id = :#{#user.id}")
    Mono<SysUser> updateUser(@Param("user") SysUser user);


    /**
     * 动态更新用户信息
     *
     * @param user
     * @return
     */
    @Query("UPDATE User SET " +
            "name = CASE WHEN :#{#user.name} IS NULL THEN name ELSE :#{#user.name} END, " +
            "age = CASE WHEN :#{#user.age} IS NULL THEN age ELSE :#{#user.age} END " +
            "WHERE id = :#{#user.id}")
    Mono<SysUser> updateUserQuery(@Param("user") SysUser user);

}
