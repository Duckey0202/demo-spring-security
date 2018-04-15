package com.mt.demo.springsecurity.manager;

import com.github.wenhao.jpa.Specifications;
import com.mt.demo.springsecurity.entity.RoleEntity;
import com.mt.demo.springsecurity.entity.UserEntity;
import com.mt.demo.springsecurity.repository.RoleRepository;
import com.mt.demo.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MyUserDetailsService
 *
 * @author MT.LUO
 * 2018/1/12 12:17
 * @Description:
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Specification<UserEntity> specification = Specifications.<UserEntity>and().eq("deleted", false).eq
                ("userName", s).build();
        UserEntity userEntity = userRepository.findOne(specification);
        if(userEntity ==  null){
            throw new UsernameNotFoundException("用户名无效");
        }
        List<GrantedAuthority> authorities = buildUserAuthority(userEntity.getRoles());
        return buildUserForAuthentication(userEntity, authorities);
    }

    private User buildUserForAuthentication(UserEntity userEntity, List<GrantedAuthority> authorities) {
        return new User(userEntity.getUserName(), userEntity.getUserPassword(), !(userEntity.isDeleted()), true, true,
                true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<RoleEntity> roleEntities) {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
       // grantedAuthorities.add(new SimpleGrantedAuthority(roleEntity.getRoleName()));
        if(roleEntities == null){
            return new ArrayList<>(grantedAuthorities);
        }
        for (RoleEntity role:roleEntities){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new ArrayList<>(grantedAuthorities);
    }

}
