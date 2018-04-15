package com.mt.demo.springsecurity.controller;

import com.mt.demo.springsecurity.entity.MenuTypeEntity;
import com.mt.demo.springsecurity.service.MenuTypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MenuTypeController
 *
 * @author MT.LUO
 * 2018/1/23 13:22
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/system/menu_type")
public class MenuTypeController extends BaseController<MenuTypeService, MenuTypeEntity, Long>{

}
