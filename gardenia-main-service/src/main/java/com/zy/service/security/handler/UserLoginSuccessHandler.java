package com.zy.service.security.handler;

import com.zy.service.utils.AccessAddressUtils;
import com.zy.service.utils.JWTTokenUtils;
import com.zy.service.utils.R;
import com.zy.service.utils.ResponseUtils;
import com.zy.service.security.beans.LoginUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * @description 用户登录成功后返回
 */
@Slf4j
//@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {

        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        // 获得请求IP
        String ip = AccessAddressUtils.getIpAddress(request);
        String token = JWTTokenUtils.createAccessToken(loginUserDetails);

        // 保存 Token 信息到 Redis 中
//        JWTTokenUtils.setTokenInfo(token, loginUserDetails.getUsername(), ip);

        log.info("用户{}登录成功，Token信息已保存到Redis", loginUserDetails.getUsername());

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("openId", loginUserDetails.getUsername());
        ResponseUtils.responseJson(response, R.ok().message("登录成功!").data("tokenMap", tokenMap));
    }
}
