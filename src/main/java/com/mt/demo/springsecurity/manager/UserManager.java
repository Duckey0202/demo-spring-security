package com.mt.demo.springsecurity.manager;

import com.alibaba.fastjson.JSONObject;
import com.github.wenhao.jpa.Specifications;
import com.mt.demo.springsecurity.entity.UserEntity;
import com.mt.demo.springsecurity.repository.UserRepository;
import com.mt.demo.springsecurity.service.UserService;
import com.mt.demo.springsecurity.utils.Constant;
import com.mt.demo.springsecurity.utils.LogController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserManager
 *
 * @author MT.LUO
 * 2018/1/9 23:15
 * @Description:
 */
@Service
public class UserManager extends BaseManager<UserEntity, Long> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /* 插入则对密码进行加密，否则不加密 */
    @Override
    public void save(UserEntity user, int flag) {
        LogController.info("UserManager", "..id:" + user.getId());
        if ((user.getUserPassword() != null) && (flag == Constant.ACTION_FLAG_INSERT)) {

            user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        }

        userRepository.save(user);
    }

    @Override
    public List<UserEntity> findDynamicParam(String key) {
        if (key != null && key.length() > 0) {

            Long id = -1l;

            if (StringUtils.isNumeric(key)) {
                id = Long.valueOf(key);
            }

            Specification<UserEntity> specification = Specifications.<UserEntity>or().eq("deleted", "0").in
                    ("user_name", key).in((id > -1l), "user_phone", id).build();
            return userRepository.findAll(specification);
        } else {
            return userRepository.findAll();
        }
    }

    @Override
    public UserEntity findByIdAndUserPassword(Long id, String password) {
        LogController.info("UserManager", "id:" + id + ",pwd:" + password);
        return userRepository.findByIdAndUserPassword(id, bCryptPasswordEncoder.encode(password));

    }

    @Override
    public Page<UserEntity> findAll(JSONObject object, Pageable pageable) {
        String userName = "";
        String userPhone = "";
        if (object.containsKey("userName")) {
            userName = object.getString("userName");
        } else if (object.containsKey("userPhone")) {
            userPhone = object.getString("userPhone");
        }

        Specification<UserEntity> specification = Specifications.<UserEntity>and().eq("deleted", false).like(
                (!userName.equals("")), "user_name", userName).like((!userPhone.equals("")), "user_phone", userPhone)
                .build();
        LogController.info("UserManager", ".....");
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public UserEntity checkPassword(Long id, String pwd) {
        UserEntity userEntity = userRepository.findOne(id);
        if (userEntity == null)
            return null;
        LogController.info("UserManager", "pwd:" + pwd + ",en:" + userEntity.getUserPassword());
        if (bCryptPasswordEncoder.matches(pwd, userEntity.getUserPassword())) {
            return userEntity;
        }
        return null;
    }

    @Override
    public UserEntity findByUserName(String username) {

        return userRepository.findByUserName(username);
    }

    @Override
    public boolean nameExsit(String name) {
        Specification<UserEntity> specification = Specifications.<UserEntity>and().eq("deleted", false).eq
                ("userName", name).build();

        if (userRepository.findOne(specification) == null) {
            return false;
        }
        return true;
    }
}
