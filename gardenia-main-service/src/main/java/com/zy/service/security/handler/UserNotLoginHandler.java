package com.zy.service.security.handler;

import com.zy.service.utils.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**

 * @author zhangyu
 * @description 用户未登录处理类
 */
//@Component
public class UserNotLoginHandler implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		ResponseUtils.responseJson(response, ResponseUtils.response(401, "未登录", authException.getMessage()));
	}
}
