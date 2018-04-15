package com.mt.demo.springsecurity.repository;

import com.mt.demo.springsecurity.entity.MenuEntity;

import java.util.List;

/**
 * MenuRepository
 *
 * @author MT.LUO
 * 2018/1/12 11:44
 * @Description:
 */
public interface MenuRepository extends BaseRepository<MenuEntity, Long>{

    MenuEntity findByMenuValue(String value);

    List<MenuEntity> findByDeleted(boolean deleted);
}
