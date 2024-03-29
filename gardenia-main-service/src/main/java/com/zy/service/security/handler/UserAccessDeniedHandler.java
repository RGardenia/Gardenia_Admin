package com.zy.service.security.handler;

import com.zy.service.utils.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangyu
 * @description 权限处理类
 */
//@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		ResponseUtils.responseJson(response, ResponseUtils.response(403, "拒绝访问",
						accessDeniedException.getMessage()));
	}

}
