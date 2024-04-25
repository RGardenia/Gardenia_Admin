package com.zy.service.sys.service;

import com.zy.service.sys.entity.SysUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author Gardenia
 * @Description TODO
 * @Date 2024/4/25 13:57
 * @Version 1.0
 */

public interface SysUserService {
    /**
     * 查询全部用户信息
     *
     * @return
     */
    Flux<SysUser> getAllUserInfo();

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return
     */
    Mono<SysUser> getUserById(String id);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    Mono<SysUser> saveUser(SysUser user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    Mono<SysUser> updateUser(SysUser user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    Mono<Void> deleteUser(String id);
}
