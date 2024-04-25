package com.zy.service.sys.service.impl;

import com.zy.service.sys.entity.SysUser;
import com.zy.service.sys.repository.SysUserRepositories;
import com.zy.service.sys.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author Gardenia
 * @Description TODO
 * @Date 2024/4/25 13:59
 * @Version 1.0
 */

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserRepositories userRepository;

    @Override
    public Flux<SysUser> getAllUserInfo() {
        return userRepository.findAll();
    }

    @Override
    public Mono<SysUser> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<SysUser> saveUser(SysUser user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<SysUser> updateUser(SysUser user) {
        return userRepository.updateUser(user);
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }
}
