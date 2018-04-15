package com.mt.demo.springsecurity.controller;

import com.mt.demo.springsecurity.entity.MenuEntity;
import com.mt.demo.springsecurity.service.MenuService;
import com.mt.demo.springsecurity.utils.LogController;
import com.mt.demo.springsecurity.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * MenuController
 *
 * @author MT.LUO
 * 2018/1/23 0:47
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/system/menu")
@Validated
public class MenuController extends BaseController<MenuService, MenuEntity, Long> {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private MenuService menuService;

    @Override
    @RequestMapping(value = "/update.action", method = RequestMethod.POST)
    public ResultModel update(@Valid MenuEntity entity, BindingResult result) {
        LogController.info("MenuController", "update.action");
        if (result.hasErrors()) {
            return ResultModel.error(result, messageSource);
        }
        if (entity.getId() != null) {
            MenuEntity menuEntity = menuService.findOne(entity.getId());
            if(menuEntity != null) {
                entity.setRoles(menuEntity.getRoles());
                baseManager.save(entity);
            }
        }
        return ResultModel.ok("success");
    }

}
