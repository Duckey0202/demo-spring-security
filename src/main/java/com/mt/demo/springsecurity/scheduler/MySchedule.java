package com.mt.demo.springsecurity.scheduler;

import com.mt.demo.springsecurity.entity.MenuEntity;
import com.mt.demo.springsecurity.repository.MenuRepository;
import com.mt.demo.springsecurity.utils.LogController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

/**
 * MySchedule
 *
 * @author MT.LUO
 * 2018/3/20 16:17
 * @Description:
 */
@Configuration
@EnableScheduling
public class MySchedule {
    private List<MenuEntity> menuEntityList = new ArrayList<>();

    @Autowired
    private MenuRepository menuRepository;

    @Scheduled(fixedRate = 5 * 60 * 1000)
    private void refreshMenuList() {
        LogController.info("MySchedule", String.valueOf(System.currentTimeMillis()));
        menuEntityList.clear();
        menuEntityList = menuRepository.findByDeleted(false);
    }

    public void firstRun() {
        LogController.info("MySchedule", "firstRun");
        menuEntityList = menuRepository.findByDeleted(false);
    }

    public List<MenuEntity> getMenuEntityList() {
        return menuEntityList;
    }
}
