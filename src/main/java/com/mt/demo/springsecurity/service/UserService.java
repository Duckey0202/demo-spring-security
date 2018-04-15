package com.mt.demo.springsecurity.service;

import com.alibaba.fastjson.JSONObject;
import com.mt.demo.springsecurity.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * UserService
 *
 * @author MT.LUO
 * 2018/1/9 23:15
 * @Description:
 */
public interface UserService extends BaseService<UserEntity, Long> {
    void save(UserEntity user, int flag);

    List<UserEntity> findDynamicParam(String key);

    UserEntity findByIdAndUserPassword(Long id, String password);

    Page<UserEntity> findAll(JSONObject object, Pageable pageable);

    UserEntity checkPassword(Long id, String pwd);

    UserEntity findByUserName(String username);

}
