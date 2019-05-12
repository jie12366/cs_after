package com.cs.demo.service;

import com.cs.demo.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.cs.demo.entity.*;
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
    private final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("phone = " + s);
        com.cs.demo.entity.User user = userService.getIdByName(s);
        if (user == null){
            user = userService.getUserByPhone(s);
        }
        List<Role> roleList = userService.getRolesByUserName(s);
        UserDetails userDetails = null;
        List<GrantedAuthority> roles = new ArrayList<>();
        for (Role role:roleList){
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }
        userDetails = new User(s,user.getPassword(),roles);
        return userDetails;
    }
}