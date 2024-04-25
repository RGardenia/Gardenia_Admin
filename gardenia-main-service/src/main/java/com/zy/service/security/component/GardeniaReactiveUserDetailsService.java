package com.zy.service.security.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @Author Gardenia
 * @Description TODO
 * @Date 2024/4/25 0:14
 * @Version 1.0
 */

@Component
public class GardeniaReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    DatabaseClient databaseClient;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return null;
    }
}
