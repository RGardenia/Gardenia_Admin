package com.zy.service.security.handler;

import com.zy.service.utils.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @description 登陆失败处理类
 */
//@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {
		ResponseUtils.responseJson(response, ResponseUtils.response(500, "登录失败", exception.getMessage()));
	}
}
