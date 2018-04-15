package com.mt.demo.springsecurity.service;

import com.alibaba.fastjson.JSONObject;
import com.mt.demo.springsecurity.entity.MenuEntity;
import com.mt.demo.springsecurity.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * UserRoleServices
 *
 * @author MT.LUO
 * 2018/1/9 23:15
 * @Description:
 */
public interface RoleService extends BaseService<RoleEntity, Long> {

    List<RoleEntity> findDynamicParam(String key);

    RoleEntity findByRoleName(String name);

    Page<RoleEntity> findAll(JSONObject object, Pageable pageable);

    void saveRoleToMenu(List<MenuEntity> menuIds, Long roleId);

    void deleteRoleToMenu(Long roleId);
}
