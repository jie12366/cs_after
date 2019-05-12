package com.cs.demo.service.impl;

import com.cs.demo.entity.Role;
import com.cs.demo.entity.User;
import com.cs.demo.mapper.UserMapper;
import com.cs.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/17 21:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int saveUser(User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        return userMapper.saveUser(user.getUserName(),password,user.getPhone());
    }

    @Override
    public int saveUserRole(int userId, int roleId) {
        return userMapper.saveUserRole(userId, roleId);
    }

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String theme, String content, String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2263509062@qq.com");
        message.setTo(to);
        message.setSubject(theme);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public int updateUser(String password, String userName) {
        return userMapper.updateUser(password, userName);
    }

    @Override
    public User getIdByName(String userName) {
        return userMapper.getIdByName(userName);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }

    @Override
    public List<Role> getRolesByUserName(String userName) {
        return userMapper.getRolesByUserName(userName);
    }
}