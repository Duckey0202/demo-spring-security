package com.mt.demo.springsecurity.manager;

import com.mt.demo.springsecurity.entity.MenuView;
import com.mt.demo.springsecurity.repository.MenuViewRepository;
import com.mt.demo.springsecurity.service.MenuViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MenuViewManager
 *
 * @author MT.LUO
 * 2018/1/23 15:27
 * @Description:
 */
@Service
public class MenuViewManager implements MenuViewService {
    @Autowired
    private MenuViewRepository menuViewRepository;
    @Override
    public List<MenuView> findByUserName(String username) {
        return menuViewRepository.findByUserName(username);
    }

    @Override
    public List<MenuView> findAll() {
        return menuViewRepository.findAll();
    }
}
