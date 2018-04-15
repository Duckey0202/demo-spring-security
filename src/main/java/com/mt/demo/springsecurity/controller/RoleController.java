package com.mt.demo.springsecurity.controller;

import com.mt.demo.springsecurity.entity.RoleEntity;
import com.mt.demo.springsecurity.service.RoleService;
import com.mt.demo.springsecurity.service.UserService;
import com.mt.demo.springsecurity.utils.LoginInfo;
import com.mt.demo.springsecurity.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * RoleController
 *
 * @author MT.LUO
 * 2018/1/23 0:39
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/system/role")
@Validated
public class RoleController extends BaseController<RoleService, RoleEntity, Long> {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @Override
    @RequestMapping(value = "/add.action", method = RequestMethod.POST)
    public ResultModel add(@RequestBody RoleEntity entity, BindingResult result) {

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(entity.getRoleName());

        if(LoginInfo.isLogin()){
            roleEntity.setActionId(userService.findByUserName(LoginInfo.getCurrentUser().getUsername()).getId());
        }else {
            roleEntity.setActionId(0l);
        }
        roleService.save(roleEntity);
        roleService.saveRoleToMenu(entity.getMenuEntities(), roleEntity.getId());
        return ResultModel.ok("success");
    }

    @Override
    @RequestMapping(value = "/update.action", method = RequestMethod.POST)
    public ResultModel update(@RequestBody RoleEntity entity, BindingResult result) {
        if (result.hasErrors()) {
            return ResultModel.error(result, messageSource);
        }

        if ((entity.getId() != null) && (roleService.findOne(entity.getId()) != null)) {
            roleService.save(entity);
            roleService.deleteRoleToMenu(entity.getId());
            roleService.saveRoleToMenu(entity.getMenuEntities(), entity.getId());
        }

        return ResultModel.ok("success");
    }
}
