package com.mt.demo.springsecurity.configuration;

import com.mt.demo.springsecurity.utils.LogController;
import com.mt.demo.springsecurity.utils.ResultCode;
import com.mt.demo.springsecurity.utils.ResultModel;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * GoAuthenticationEntryPoint
 *
 * @author MT.LUO
 * 2018/1/21 22:57
 * @Description:
 */
@Component
public class GoAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException
            authException) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        LogController.info("GoAuthenticationEntryPoint", authException.getMessage());
        response.getWriter().print(ResultModel.error(ResultCode.RESULT_CODE_LOGIN_ERROR, authException.getMessage()));

        response.getWriter().flush();
    }
}
