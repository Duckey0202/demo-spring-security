package com.mt.demo.springsecurity.manager;

import com.alibaba.fastjson.JSONObject;
import com.github.wenhao.jpa.Specifications;
import com.mt.demo.springsecurity.entity.MenuEntity;
import com.mt.demo.springsecurity.repository.MenuRepository;
import com.mt.demo.springsecurity.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MenuManager
 *
 * @author MT.LUO
 * 2018/1/12 11:45
 * @Description:
 */
@Service
public class MenuManager extends BaseManager<MenuEntity, Long> implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public MenuEntity findByMenuValue(String value) {
        return menuRepository.findByMenuValue(value);
    }

    @Override
    public List<MenuEntity> findByDeleted(boolean deleted) {
        return menuRepository.findByDeleted(deleted);
    }

    @Override
    public Page<MenuEntity> findAll(JSONObject object, Pageable pageable) {

        Specification<MenuEntity> specification = Specifications.<MenuEntity>and().eq("deleted", false).build();
        return menuRepository.findAll(specification, pageable);
    }
}
