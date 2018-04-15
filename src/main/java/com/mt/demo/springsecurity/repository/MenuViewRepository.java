package com.mt.demo.springsecurity.repository;

import com.mt.demo.springsecurity.entity.MenuView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * MenuViewRepository
 *
 * @author MT.LUO
 * 2018/1/23 15:12
 * @Description:
 */
public interface MenuViewRepository extends JpaRepository<MenuView, Long>, JpaSpecificationExecutor<MenuView>, Serializable {
    List<MenuView> findByUserName(String username);
}
