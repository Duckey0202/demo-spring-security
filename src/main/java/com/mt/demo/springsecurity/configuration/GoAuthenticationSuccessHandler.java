package com.mt.demo.springsecurity.configuration;

import com.mt.demo.springsecurity.utils.LogController;
import com.mt.demo.springsecurity.utils.ResultModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * GoAuthenticationSuccessHandler
 *
 * @author MT.LUO
 * 2018/1/21 22:52
 * @Description:
 */
@Component
public class GoAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication
            authentication) throws IOException, ServletException {
        LogController.info("GoAuthenticationSuccessHandler", "GoAuthenticationSuccessHandler");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(ResultModel.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .toString()).toString());
        out.flush();
        out.close();
    }
}
