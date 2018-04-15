package com.mt.demo.springsecurity.service;

import com.mt.demo.springsecurity.entity.MenuView;

import java.util.List;

/**
 * MenuViewService
 *
 * @author MT.LUO
 * 2018/1/23 15:26
 * @Description:
 */
public interface MenuViewService {
    List<MenuView> findByUserName(String username);

    List<MenuView> findAll();

}
