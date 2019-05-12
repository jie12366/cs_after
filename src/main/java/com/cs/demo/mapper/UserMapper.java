package com.cs.demo.mapper;

import com.cs.demo.entity.Role;
import com.cs.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/17 21:34
 */
@Mapper
public interface UserMapper {

    /**
     * 注册新用户（学生、商家、管理员）
     * @param userName 用户名
     * @param password 密码
     * @param phone
     * @return int
     */
    @Insert("insert into user(userName,password,phone) values(#{userName},#{password},#{phone})")
    int saveUser(String userName,String password,String phone);

    /**
     * 给新注册的用户分配角色
     * @param userId 用户id
     * @param roleId 角色id
     * @return int
     */
    @Insert("insert into user_role(user_id,role_id) values(#{userId},#{roleId})")
    int saveUserRole(int userId,int roleId);

    /**
     * 重置密码
     * @param password 新密码
     * @param userName 用户名
     * @return
     */
    @Update("update user set password=#{password} where userName=#{userName}")
    int updateUser(String password,String userName);

    /**
     * 根据用户名获取该账号的id
     * @param userName 用户名
     * @return int
     */
    @Select("select * from user where userName=#{userName}")
    User getIdByName(String userName);

    /**
     * 根据手机号获取用户信息
     * @param phone
     * @return
     */
    @Select("select * from user where phone=#{phone}")
    User getUserByPhone(String phone);

    /**
     * 获取所有的用户
     * @return List
     */
    @Select("select * from user")
    List<User> listUser();

    /**
     * 根据用户名获取该用户的权限
     * @param userName 用户名
     * @return User
     */
    @Select("select r.* from user u " +
            "LEFT JOIN user_role ur on u.id=ur.user_id " +
            "LEFT JOIN role r on ur.role_id=r.id " +
            "where u.userName=#{userName}")
    List<Role> getRolesByUserName(String userName);
}