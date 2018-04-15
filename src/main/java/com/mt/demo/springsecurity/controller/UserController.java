package com.mt.demo.springsecurity.controller;

import com.mt.demo.springsecurity.entity.UserEntity;
import com.mt.demo.springsecurity.manager.RoleManager;
import com.mt.demo.springsecurity.manager.UserManager;
import com.mt.demo.springsecurity.service.UserService;
import com.mt.demo.springsecurity.utils.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * UserController
 *
 * @author MT.LUO
 * 2018/1/12 17:15
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/system/user")
@Validated
public class UserController extends BaseController<UserService, UserEntity, Long> {
    @Autowired
    UserManager userManager;
    @Autowired
    RoleManager roleManager;

    @Autowired
    private MessageSource messageSource;

    @Override
    @RequestMapping(value = "/add.action", method = RequestMethod.POST)
    public ResultModel add(@Valid UserEntity entity, BindingResult result) {
        LogController.info("UserController", "save.action" + entity.getUserName());
        if (result.hasErrors()) {
            return ResultModel.error(result, messageSource);
        }
        if (userManager.nameExsit(entity.getUserName())) {
            return ResultModel.error(ResultCode.RESULT_CODE_USERNAME_EXIST, "用户名已存在");
        }
        LogController.info("UserController", entity.toString());
        if (LoginInfo.isLogin()) {
            entity.setActionId(userManager.findByUserName(LoginInfo.getCurrentUser().getUsername()).getId());
        } else {
            entity.setActionId(0l);
        }

        userManager.save(entity, Constant.ACTION_FLAG_INSERT);
        return ResultModel.ok("success");
    }

    @Override
    @RequestMapping(value = "/update.action", method = RequestMethod.POST)
    public ResultModel update(@Valid UserEntity entity, BindingResult result) {
        LogController.info("UserController", "save.action" + entity.getUserName());
        if (result.hasErrors()) {
            return ResultModel.error(result, messageSource);
        }
        LogController.info("UserController", entity.toString());

        if (entity.getId() != null) {
            UserEntity userEntity = userManager.findOne(entity.getId());
            if (userEntity != null) {
            /* 用户信息修改不可直接修改密码，修改密码需要验证原密码 */
                entity.setUserPassword(userEntity.getUserPassword());
                userManager.save(entity, Constant.ACTION_FLAG_UPDATE);
                return ResultModel.ok("success");
            }
        }
        return ResultModel.error(ResultCode.RESULT_CODE_USER_NOT_EXIST, "user is not exist");
    }

    @Validated
    @RequestMapping(value = "/modifypwd.action", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel modifyPassword(@RequestParam @NotNull(message = "password not null") @Length(max = 60, message
            = "password too long") String newPwd, @RequestParam String oldPwd, @RequestParam @NotNull(message = "id "
            + "not null") Long id) {
        LogController.info("UserController", "modifyPwassword.action" + newPwd + ",o:" + oldPwd);

        UserEntity userEntity = userManager.checkPassword(id, oldPwd);
        if (userEntity == null) {
            return ResultModel.error(ResultCode.RESULT_CODE_ERROR, "密码或用户名错误");
        }
        userEntity.setUserPassword(newPwd);
        userManager.save(userEntity, Constant.ACTION_FLAG_INSERT);
        return ResultModel.ok("success");
    }

    @RequestMapping(value = "/resetpwd.action", method = RequestMethod.POST)
    public ResultModel resetPassword(@RequestParam @NotNull(message = "password not null") @Length(max = 60, message
            = "password too long") String newPwd, @RequestParam @NotNull(message = "id not " +
            "null") Long id) {

        UserEntity userEntity = userManager.findOne(id);
        if (userEntity == null) {
            return ResultModel.error(ResultCode.RESULT_CODE_ERROR, "用户不存在");
        }
        userEntity.setUserPassword(newPwd);
        userManager.save(userEntity, Constant.ACTION_FLAG_INSERT);
        return ResultModel.ok("success");
    }

}
