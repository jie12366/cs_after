package com.cs.demo.service;

import com.cs.demo.entity.StudentMessage;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/8 19:07
 */
@Service
public interface StudentMessageService {

    /**
     * 保存用户信息
     * @param nickName
     * @param avatar
     * @param sex
     * @param name
     * @param introduction
     * @param address
     * @param school
     * @param society
     * @param social
     * @param socialNumber
     * @param userName
     * @return
     */
    int saveStudentMessage(String userName,String nickName,String avatar,String sex,String name,String introduction,String address,String school,
                           String society,String social,String socialNumber);

    /**
     * 根据id更新用户信息
     * @param nickName
     * @param avatar
     * @param sex
     * @param name
     * @param introduction
     * @param address
     * @param school
     * @param society
     * @param social
     * @param socialNumber
     * @param userName
     * @return
     */
    int updateStudentMessage(String nickName,String avatar,String sex,String name,String introduction,String address,String school,
                             String society,String social,String socialNumber,String userName);

    /**
     * 根据id获取用户信息
     * @param userName
     * @return
     */
    @Select("select * from student_message where userName=#{userName}")
    StudentMessage getStudentMessageById(String userName);
}