package com.mt.demo.springsecurity.repository;


import com.mt.demo.springsecurity.entity.MenuTypeEntity;

/**
 * MenuTypeRepository
 *
 * @author MT.LUO
 * 2018/1/23 13:17
 * @Description:
 */
public interface MenuTypeRepository extends BaseRepository<MenuTypeEntity, Long> {
    MenuTypeEntity findByMenuTypeText(String name);
}
