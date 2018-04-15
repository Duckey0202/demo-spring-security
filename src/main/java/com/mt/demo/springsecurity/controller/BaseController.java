package com.mt.demo.springsecurity.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.wenhao.jpa.Specifications;
import com.mt.demo.springsecurity.entity.BaseEntity;
import com.mt.demo.springsecurity.entity.Pagination;
import com.mt.demo.springsecurity.manager.UserManager;
import com.mt.demo.springsecurity.service.BaseService;
import com.mt.demo.springsecurity.utils.LogController;
import com.mt.demo.springsecurity.utils.LoginInfo;
import com.mt.demo.springsecurity.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * BaseController
 *
 * @author MT.LUO
 * 2018/1/24 11:18
 * @Description:
 */
public abstract class BaseController<S extends BaseService<T, ID>, T extends BaseEntity<T>, ID extends Serializable> {

    @Autowired
    protected S baseManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/list.action", method = RequestMethod.GET)
    public ResultModel getList(@Valid Pagination pagination, BindingResult result) {
        if (result.hasErrors()) {
            return ResultModel.error(result, messageSource);
        }

        LogController.info("BaseController", LoginInfo.getCurrentUser().getUsername());
        Specification<T> specification = Specifications.<T>and().eq("deleted", false).build();

        Sort sort = pagination.getSort();
        if (pagination.getPageSize() > 0) {
            if (sort == null) {
                Pageable pageable = new PageRequest(pagination.getPageNumber(), pagination.getPageSize());
                Page<T> page = baseManager.findAll(specification, pageable, pagination.getSearch());
                return ResultModel.ok("success", page);
            } else {
                Pageable pageable = new PageRequest(pagination.getPageNumber(), pagination.getPageSize(), sort);
                Page<T> page = baseManager.findAll(specification, pageable, pagination.getSearch());
                return ResultModel.ok("success", page);
            }

        } else {
            List<T> entities = baseManager.findAll(specification);
            return ResultModel.ok("success", entities);
        }
    }

    @RequestMapping(value = "/add.action", method = RequestMethod.POST)
    public ResultModel add(@Valid T entity, BindingResult result) {
        LogController.info("BaseController", "save.action");
        if (result.hasErrors()) {
            return ResultModel.error(result, messageSource);
        }
        LogController.info("BaseController", entity.toString());
        if (LoginInfo.isLogin()) {
            entity.setActionId(userManager.findByUserName(LoginInfo.getCurrentUser().getUsername()).getId());
        } else {
            entity.setActionId(0l);
        }
        baseManager.save(entity);
        return ResultModel.ok("success");
    }

    @RequestMapping(value = "/update.action", method = RequestMethod.POST)
    public ResultModel update(@Valid T entity, BindingResult result) {
        LogController.info("BaseController", "update.action");
        if (result.hasErrors()) {
            return ResultModel.error(result, messageSource);
        }
        LogController.info("BaseController", entity.toString());
        if ((entity.getId() != null) && (baseManager.findOne((ID) entity.getId()) != null)) {

            baseManager.save(entity);
        }
        return ResultModel.ok("success");
    }

    @RequestMapping(value = "/delete.action", method = RequestMethod.POST)
    public ResultModel delete(@RequestParam ID id) {
        LogController.info("BaseController", "delete.action");
        baseManager.setDeletedTrue(id);
        return ResultModel.ok("success");
    }

    @RequestMapping(value = "/valid/name.action", method = RequestMethod.POST)
    public ResultModel valid(@RequestParam String name) {
        LogController.info("BaseController", "delete.action");

        JSONObject object = new JSONObject();
        object.put("result", baseManager.nameExsit(name));

        return ResultModel.ok("success", object);
    }

    @RequestMapping(value = "/test.action", method = RequestMethod.POST)
    public ResultModel test(@RequestParam ID id) {
        LogController.info("BaseController", "test.action");
        baseManager.test(id);
        return ResultModel.ok("success");
    }
}
