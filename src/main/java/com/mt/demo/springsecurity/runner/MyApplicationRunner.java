package com.mt.demo.springsecurity.runner;

import com.mt.demo.springsecurity.entity.MenuEntity;
import com.mt.demo.springsecurity.entity.MenuTypeEntity;
import com.mt.demo.springsecurity.entity.RoleEntity;
import com.mt.demo.springsecurity.entity.UserEntity;
import com.mt.demo.springsecurity.manager.MenuManager;
import com.mt.demo.springsecurity.manager.MenuTypeManager;
import com.mt.demo.springsecurity.manager.RoleManager;
import com.mt.demo.springsecurity.manager.UserManager;
import com.mt.demo.springsecurity.scheduler.MySchedule;
import com.mt.demo.springsecurity.utils.Constant;
import com.mt.demo.springsecurity.utils.LogController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * MyApplicationRunner
 *
 * @author MT.LUO
 * 2018/1/18 14:04
 * @Description:
 */
@Component
@Order(value = 1)
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    private UserManager userManager;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private MenuManager menuManager;
    @Autowired
    private MenuTypeManager menuTypeManager;

    @Autowired
    private MySchedule mySchedule;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        LogController.info("MyApplicationRunner", "run....");
        RoleEntity roleEntity = roleManager.findByRoleName("ROLE_ADMIN");
        if(roleEntity == null){
            roleEntity = new RoleEntity();
            roleEntity.setRoleName("ROLE_ADMIN");
            roleEntity.setActionId(0l);
            roleManager.save(roleEntity);
        }
        List<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(roleEntity);
        List<UserEntity> userEntitys =userManager.findAll();
        if (userEntitys.size() == 0){
            UserEntity user = new UserEntity();
            user.setUserName("unionman");
            user.setUserPassword("123456");
            user.setRoles(roleEntities);
            user.setActionId(0l);
            userManager.save(user, Constant.ACTION_FLAG_INSERT);
        }

        List<MenuTypeEntity> menuTypeEntities = menuTypeManager.findAll();
        if(menuTypeEntities.size() == 0){
            MenuTypeEntity menuTypeEntity = new MenuTypeEntity();
            menuTypeEntity.setMenuTypeText("content");
            menuTypeEntity.setMenuTypeValue(1);
            menuTypeEntity.setActionId(0l);
            menuTypeManager.save(menuTypeEntity);

            MenuTypeEntity menuTypeEntity1 = new MenuTypeEntity();
            menuTypeEntity1.setMenuTypeText("menu");
            menuTypeEntity1.setMenuTypeValue(2);
            menuTypeEntity1.setActionId(0l);
            menuTypeManager.save(menuTypeEntity1);

            MenuTypeEntity menuTypeEntity2 = new MenuTypeEntity();
            menuTypeEntity2.setMenuTypeText("botton");
            menuTypeEntity2.setMenuTypeValue(3);
            menuTypeEntity2.setActionId(0l);
            menuTypeManager.save(menuTypeEntity2);

        }
        List<MenuEntity> menuEntities = menuManager.findAll();
        if(menuEntities.size() == 0){
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.setActionId(0l);
            menuEntity.setMenuTitle("NB-IOT PLATFORM");
            menuEntity.setMenuOrder(0);
            menuEntity.setMenuLevel(0);
            menuEntity.setMenuType(1);
            menuEntity.setMenuParentId(0l);
            menuEntity.setMenuPath("/");
           // menuEntity.setRoles(roleEntities);
            menuManager.save(menuEntity);

            MenuEntity menuEntity1 = new MenuEntity();
            menuEntity1.setActionId(0l);
            menuEntity1.setMenuTitle("SYSTEM");
            menuEntity1.setMenuOrder(0);
            menuEntity1.setMenuLevel(1);
            menuEntity1.setMenuType(1);
            menuEntity1.setMenuParentId(menuEntity.getId());
            menuEntity1.setMenuValue("/system");
            menuEntity1.setMenuPath("/system");
            menuEntity1.setMenuComponent("SYSTEM");
            menuEntity1.setMenuRequireAuth(true);
            menuEntity1.setMenuKeepAlive(true);
            //menuEntity1.setRoles(roleEntities);
            menuManager.save(menuEntity1);

            MenuEntity menuEntity2 = new MenuEntity();
            menuEntity2.setActionId(0l);
            menuEntity2.setMenuTitle("MENU");
            menuEntity2.setMenuOrder(0);
            menuEntity2.setMenuLevel(2);
            menuEntity2.setMenuType(2);
            menuEntity2.setMenuParentId(menuEntity1.getId());
            menuEntity2.setMenuValue("/system/menu");
            menuEntity2.setMenuPath("/system/menu/");
            menuEntity2.setMenuComponent("SYSTEM");
            menuEntity2.setMenuRequireAuth(true);
            menuEntity2.setMenuKeepAlive(true);
          //  menuEntity2.setRoles(roleEntities);
            menuManager.save(menuEntity2);

            MenuEntity menuEntity4 = new MenuEntity();
            menuEntity4.setActionId(0l);
            menuEntity4.setMenuTitle("LIST");
            menuEntity4.setMenuOrder(0);
            menuEntity4.setMenuLevel(3);
            menuEntity4.setMenuType(3);
            menuEntity4.setMenuParentId(menuEntity2.getId());
            menuEntity4.setMenuValue("/system/menu/list.action");
            menuEntity4.setMenuPath("/system/menu/list");
            menuEntity4.setMenuComponent("SYSTEM");
            menuEntity4.setMenuRequireAuth(true);
            menuEntity4.setMenuKeepAlive(true);
         ///   menuEntity4.setRoles(roleEntities);
            menuManager.save(menuEntity4);

            MenuEntity menuEntity3 = new MenuEntity();
            menuEntity3.setActionId(0l);
            menuEntity3.setMenuTitle("ADD");
            menuEntity3.setMenuOrder(0);
            menuEntity3.setMenuLevel(3);
            menuEntity3.setMenuType(3);
            menuEntity3.setMenuParentId(menuEntity2.getId());
            menuEntity3.setMenuValue("/system/menu/add.action");
            menuEntity3.setMenuPath("/system/menu/add");
            menuEntity3.setMenuComponent("SYSTEM");
            menuEntity3.setMenuRequireAuth(true);
            menuEntity3.setMenuKeepAlive(true);
        ///    menuEntity3.setRoles(roleEntities);
            menuManager.save(menuEntity3);

        }

        mySchedule.firstRun();
    }
}
