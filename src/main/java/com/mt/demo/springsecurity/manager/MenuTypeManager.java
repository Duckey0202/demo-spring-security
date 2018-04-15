package com.mt.demo.springsecurity.manager;

import com.mt.demo.springsecurity.entity.MenuTypeEntity;
import com.mt.demo.springsecurity.repository.MenuTypeRepository;
import com.mt.demo.springsecurity.service.MenuTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MenuTypeManager
 *
 * @author MT.LUO
 * 2018/1/23 13:20
 * @Description:
 */
@Service
public class MenuTypeManager extends BaseManager<MenuTypeEntity, Long> implements MenuTypeService {

    @Autowired
    private MenuTypeRepository menuTypeRepository;

    @Override
    public boolean nameExsit(String name) {
        if(menuTypeRepository.findByMenuTypeText(name) == null){
            return false;
        }
        return true;
    }
}
