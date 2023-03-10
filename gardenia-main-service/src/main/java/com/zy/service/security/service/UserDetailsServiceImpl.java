package com.zy.service.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.service.security.beans.LoginUserDetails;
import com.zy.service.sys.entity.SysUser;
import com.zy.service.sys.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zyGardenia - 栀
 * @Since 2022/4/11 13:58
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        SysUser user = userService.getOne(wrapper);

        if(user == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }

        LoginUserDetails loginUserDetails = new LoginUserDetails();
        BeanUtils.copyProperties(user, loginUserDetails);

        Set<GrantedAuthority> authorities = new HashSet<>();

        loginUserDetails.setAuthorities(authorities);

        return loginUserDetails;
    }
}
