package com.mt.demo.springsecurity.configuration;

import com.mt.demo.springsecurity.utils.LogController;
import com.mt.demo.springsecurity.utils.ResultCode;
import com.mt.demo.springsecurity.utils.ResultModel;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * GoAuthenticationFailureHandler
 *
 * @author MT.LUO
 * 2018/1/21 22:51
 * @Description:
 */
@Component
public class GoAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        LogController.info("GoAuthenticationFailureHandler", "GoAuthenticationFailureHandler");
        response.setContentType("application/json;charset=utf-8");
        Enumeration<String> em =  request.getParameterNames();
        while (em.hasMoreElements()){
            String name = em.nextElement();
            Object object= request.getParameter(name);
            LogController.info("GoAuthenticationFailureHandler", "name: "+name+",value:"+object.toString());
        }

        PrintWriter out = response.getWriter();
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            LogController.info("GoAuthenticationFailureHandler", "11: " + ResultModel.error(ResultCode.RESULT_CODE_LOGIN_FAIL,
                    "用户名或密码输入错误，登录失败!").toString());
            out.write(ResultModel.error(ResultCode.RESULT_CODE_LOGIN_FAIL, "用户名或密码输入错误，登录失败!").toString());

        } else if (exception instanceof DisabledException) {
            LogController.info("GoAuthenticationFailureHandler", "22: " + ResultModel.error(ResultCode.RESULT_CODE_AUTH_LIMIT,
                    "用户名或密码输入错误，登录失败!").toString());
            out.write(ResultModel.error(ResultCode.RESULT_CODE_AUTH_LIMIT, "账户受限，登录失败，请联系管理员!").toString());
        } else {
            LogController.info("GoAuthenticationFailureHandler", "33: " + ResultModel.error(ResultCode.RESULT_CODE_LOGIN_ERROR,
                    "用户名或密码输入错误，登录失败!").toString());
            out.write(ResultModel.error(ResultCode.RESULT_CODE_LOGIN_ERROR, "未知错误，登录失败，请联系管理员!").toString());
        }
        out.flush();
        out.close();

    }
}
