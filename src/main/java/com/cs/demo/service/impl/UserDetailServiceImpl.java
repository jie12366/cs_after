package com.cs.demo.service.impl;

import com.cs.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/7 23:07
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.cs.demo.entity.User user = userService.getIdByName(s);
        if (user == null){
            user = userService.getUserByPhone(s);
        }

        List<Role> roleList = userService.getRolesByUserName(user.getUserName());
        List<GrantedAuthority> roles = new ArrayList<>();
        for (Role role:roleList){
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(user.getUserName(),user.getPassword(),roles);
    }
}