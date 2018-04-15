package com.mt.demo.springsecurity.manager;

import com.alibaba.fastjson.JSONObject;
import com.github.wenhao.jpa.Specifications;
import com.mt.demo.springsecurity.entity.MenuEntity;
import com.mt.demo.springsecurity.entity.RoleEntity;
import com.mt.demo.springsecurity.entity.RoleToMenuEntity;
import com.mt.demo.springsecurity.repository.RoleRepository;
import com.mt.demo.springsecurity.repository.RoleToMenuRepository;
import com.mt.demo.springsecurity.scheduler.MySchedule;
import com.mt.demo.springsecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserRoleManager
 *
 * @author MT.LUO
 * 2018/1/9 23:15
 * @Description:
 */
@Service
public class RoleManager extends BaseManager<RoleEntity, Long> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleToMenuRepository roleToMenuRepository;

    @Autowired
    private MySchedule mySchedule;

    @Override
    public List<RoleEntity> findDynamicParam(String key) {
        return null;
    }

    @Override
    public RoleEntity findByRoleName(String name) {
        return roleRepository.findByRoleName(name);
    }

    @Override
    public boolean nameExsit(String name) {
        if(roleRepository.findByRoleName(name) == null){
            return false;
        }
        return true;
    }
    @Override
    public Page<RoleEntity> findAll(JSONObject object, Pageable pageable) {
        String userName = "";
        if (object.containsKey("roleName")) {
            userName = object.getString("userName");
        }

        Specification<RoleEntity> specification = Specifications.<RoleEntity>and().eq("deleted", false).like(
                (!userName.equals("")), "role_name", userName).build();
        return roleRepository.findAll(specification, pageable);
    }

    @Override
    public void saveRoleToMenu(List<MenuEntity> menuids, Long roleid) {

        List<RoleToMenuEntity> roles = new ArrayList<>();
        for (MenuEntity menuid : menuids) {
            RoleToMenuEntity role = new RoleToMenuEntity();
            role.setMenuEntityId(menuid.getId());
            role.setRolesId(roleid);
            roles.add(role);
        }
        roleToMenuRepository.save(roles);
    }

    @Override
    public void deleteRoleToMenu(Long roleId) {
        roleToMenuRepository.deleteAllByRolesId(roleId);
    }

    @Override
    public Page<RoleEntity> findAll(Specification<RoleEntity> specification, Pageable pageable, JSONObject object){
        Page<RoleEntity> roleEntities =  roleRepository.findAll(specification, pageable);
        List<RoleToMenuEntity> roleToMenuEntityList = roleToMenuRepository.findAll();
        List<MenuEntity> menuEntityList = mySchedule.getMenuEntityList();

        for (int i = 0; i<roleEntities.getContent().size(); i++){
            List<Long> menuIds = new ArrayList<>();
            for (int j = 0 ; j<roleToMenuEntityList.size(); j++){
                if(roleEntities.getContent().get(i).getId() == roleToMenuEntityList.get(j).getRolesId()){
                    menuIds.add(roleToMenuEntityList.get(j).getMenuEntityId());
                    roleToMenuEntityList.remove(j);
                    j--;
                }
            }

            if(menuIds.size()>0){
                List<MenuEntity> menuEntities = new ArrayList<>();
                for (int k =0; k<menuEntityList.size(); k++){
                    MenuEntity menuEntity =  menuEntityList.get(k);
                    for (Long id: menuIds){
                        if(id == menuEntityList.get(k).getId()){
                            menuEntities.add(menuEntity);
                            menuIds.remove(id);
                            break;
                        }
                    }
                    if(menuIds.size() == 0) break;
                }
                if(menuEntities.size()>0){
                    roleEntities.getContent().get(i).setMenuEntities(menuEntities);
                }
            }
        }
        return roleEntities;
    }

}
