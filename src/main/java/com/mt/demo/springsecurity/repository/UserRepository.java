package com.mt.demo.springsecurity.repository;

import com.mt.demo.springsecurity.entity.UserEntity;

public interface UserRepository extends BaseRepository<UserEntity, Long> {

    UserEntity findByUserName(String name);

    UserEntity findByIdAndUserPassword(Long id, String pwd);
}
