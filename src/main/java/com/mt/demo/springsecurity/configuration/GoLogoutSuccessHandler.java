package com.mt.demo.springsecurity.configuration;

import com.mt.demo.springsecurity.utils.ResultModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * GoLogoutSuccessHandler
 *
 * @author MT.LUO
 * 2018/1/21 23:00
 * @Description:
 */
@Component
public class GoLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print(ResultModel.ok("已注销"));
        response.getWriter().flush();
    }
}
