package com.mt.demo.springsecurity.configuration;

import com.mt.demo.springsecurity.utils.LogController;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * UrlAccessDecisionManager
 *
 * @author MT.LUO
 * 2018/1/12 10:27
 * @Description:
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        LogController.info("UrlAccessDecisionManager", "...");
        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            //当前请求资源需要的权限
            String needRole = configAttribute.getAttribute();
            LogController.info("UrlAccessDecisionManager", "needRole:" + needRole);
            LogController.info("UrlAccessDecisionManager", authentication.toString());

            if (authentication instanceof AnonymousAuthenticationToken) {
                throw new BadCredentialsException("未登录");
            }
            //当前用户具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            LogController.info("UrlAccessDecisionManager", authorities.toString());
            for (GrantedAuthority authority : authorities) {
                LogController.info("UrlAccessDecisionManager", ":" + authority.getAuthority());
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    return;
                }
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
