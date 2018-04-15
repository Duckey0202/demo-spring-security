package com.mt.demo.springsecurity.controller;

import com.mt.demo.springsecurity.entity.MenuEntity;
import com.mt.demo.springsecurity.entity.MenuView;
import com.mt.demo.springsecurity.service.MenuService;
import com.mt.demo.springsecurity.service.MenuViewService;
import com.mt.demo.springsecurity.utils.LogController;
import com.mt.demo.springsecurity.utils.LoginInfo;
import com.mt.demo.springsecurity.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * LoginController
 *
 * @author MT.LUO
 * 2018/1/23 14:50
 * @Description:
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private MenuViewService menuViewService;

    @Autowired
    private MenuService menuService;

    /* 获取用户登陆后有权限的菜单 */
    @RequestMapping(value = "/login/menu.action", method = RequestMethod.GET)
    public ResultModel getMenuList() {

        if (LoginInfo.isLogin()) {

            Collection<? extends GrantedAuthority> authorities = LoginInfo.getCurrentUserAuthority();
            for (GrantedAuthority authority : authorities) {
                LogController.info("UrlAccessDecisionManager", ":" + authority.getAuthority());
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    List<MenuEntity> menuEntities = menuService.findByDeleted(false);
                    return ResultModel.ok("success", menuEntities);

                }

            }
            List<MenuView> menuViews = menuViewService.findByUserName(LoginInfo.getCurrentUser().getUsername());
            return ResultModel.ok("success", menuViews);
        }
        throw new BadCredentialsException("未登录");
    }

}
