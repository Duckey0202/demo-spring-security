package com.mt.demo.springsecurity.service;

import com.alibaba.fastjson.JSONObject;
import com.mt.demo.springsecurity.entity.MenuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * MenuService
 *
 * @author MT.LUO
 * 2018/1/12 11:45
 * @Description:
 */
public interface MenuService extends BaseService<MenuEntity, Long> {

    MenuEntity findByMenuValue(String value);

    List<MenuEntity> findByDeleted(boolean deleted);

    Page<MenuEntity> findAll(JSONObject object, Pageable pageable);
}
