package com.mt.demo.springsecurity.repository;

import com.mt.demo.springsecurity.entity.RoleEntity;

public interface RoleRepository extends BaseRepository<RoleEntity, Long> {

    RoleEntity findByRoleName(String name);

}
