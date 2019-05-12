package com.cs.demo.service;

import com.cs.demo.entity.Role;
import com.cs.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/17 21:56
 */
@Service
public interface UserService {

    /**
     * 注册新用户（学生、商家、管理员）
     * @param user User
     * @return int
     */
    int saveUser(User user);

    /**
     * 给新注册的用户分配角色
     * @param userId 用户id
     * @param roleId 角色id
     * @return int
     */
    int saveUserRole(int userId,int roleId);

    /**
     * 发送邮件的接口
     *
     * @param to      发送对象
     * @param theme   邮件主题
     * @param content 邮件内容
     */
    void sendEmail(String theme, String content, String to);

    /**
     * 重置密码
     * @param password 新密码
     * @param userName 用户名
     * @return
     */
    int updateUser(String password,String userName);

    /**
     * 根据用户名获取该账号的id
     * @param userName 用户名
     * @return int
     */
    User getIdByName(String userName);

    /**
     * 根据手机号获取用户信息
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 获取所有的用户
     * @return List
     */
    List<User> listUser();

    /**
     * 根据用户名获取该用户的权限
     * @param userName 用户名
     * @return User
     */
    List<Role> getRolesByUserName(String userName);
}