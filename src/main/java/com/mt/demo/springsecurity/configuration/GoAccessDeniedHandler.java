package com.mt.demo.springsecurity.configuration;

import com.mt.demo.springsecurity.utils.LogController;
import com.mt.demo.springsecurity.utils.ResultCode;
import com.mt.demo.springsecurity.utils.ResultModel;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AuthenticationAccessDeniedHandler
 *
 * @author MT.LUO
 * 2018/1/12 10:34
 * @Description:
 */
@Component
public class GoAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException
            accessDeniedException) throws IOException, ServletException {
        LogController.info("AuthenticationAccessDeniedHandler","AuthenticationAccessDeniedHandler");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        LogController.info("AuthenticationAccessDeniedHandler","44: "+ResultModel.error(ResultCode.RESULT_CODE_AUTH_LIMIT, "权限不足，请联系管理员!").toString());
        pw.write(ResultModel.error(ResultCode.RESULT_CODE_AUTH_LIMIT, "权限不足，请联系管理员!").toString());
        pw.flush();
        pw.close();
    }
}
