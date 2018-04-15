package com.mt.demo.springsecurity.configuration;

import com.mt.demo.springsecurity.entity.MenuEntity;
import com.mt.demo.springsecurity.entity.RoleEntity;
import com.mt.demo.springsecurity.scheduler.MySchedule;
import com.mt.demo.springsecurity.utils.LogController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * UrlFilterInvocationSecurityMetadataSource
 *
 * @author MT.LUO
 * 2018/1/12 10:21
 * @Description:
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MySchedule mySchedule;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        LogController.info("UrlFilterInvocationSecurityMetadataSource", ": " + requestUrl);
        LogController.info("UrlFilterInvocationSecurityMetadataSource", ": " + ((FilterInvocation) o).getRequest()
                .getMethod());
        if ("/login".equals(requestUrl)) {
            return null;
        } else if ("/system/user/add.action".equals(requestUrl)) {
            return null;
        } else if (antPathMatcher.match("/druid/**", requestUrl)) {
            return null;
        }

        List<MenuEntity> menuList = mySchedule.getMenuEntityList();
        String reqUrl = requestUrl;
        if (requestUrl.indexOf("?") > 0) {
            reqUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
        }
        LogController.info("UrlFilterInvocation", reqUrl);

        for (MenuEntity menuEntity : menuList) {

            //比较用户是否具有该url的访问权限，如果有，则赋予该权限
            if (antPathMatcher.match(menuEntity.getMenuPath(), reqUrl)) {
                String[] values = new String[menuEntity.getRoles().size()];
                int i = 0;
                for (RoleEntity role : menuEntity.getRoles()) {
                    LogController.info("UrlFilterInvocationSecurityMetadataSource", "role.getRoleName:" + role
                            .getRoleName());
                    values[i++] = role.getRoleName();
                }
                return SecurityConfig.createList(values);
            }
        }
        return SecurityConfig.createList("ROLE_ADMIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
