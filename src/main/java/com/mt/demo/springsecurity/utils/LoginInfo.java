package com.mt.demo.springsecurity.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * LoginInfo
 *
 * @author MT.LUO
 * 2018/1/22 18:42
 * @Description:
 */
public class LoginInfo {

    public static UserDetails getCurrentUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            throw new BadCredentialsException("未登录");
        } else if (auth == null) {
            throw new BadCredentialsException("未登录");
        }
        UserDetails user = (UserDetails) auth.getPrincipal();
        return user;
    }

    public static Collection<? extends GrantedAuthority> getCurrentUserAuthority() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        return authorities;
    }

    public static boolean isLogin() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return false;
        } else if (auth == null) {
            return false;
        } else {
            return true;
        }
    }
}
