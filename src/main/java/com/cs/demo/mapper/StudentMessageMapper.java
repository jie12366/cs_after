package com.cs.demo.mapper;

import com.cs.demo.entity.StudentMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/8 18:03
 */
@Mapper
public interface StudentMessageMapper {

    /**
     * 插入用户信息
     * @param userName
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
     * @return
     */
    @Insert("insert into student_message(userName,nickName,avatar,sex,name,introduction,address,school,society,social,socialNumber) " +
            "values(#{userName},#{nickName},#{avatar},#{sex},#{name},#{introduction},#{address},#{school},#{society},#{social},#{socialNumber})")
    int saveStudentMessage(String userName,String nickName,String avatar,String sex,String name,String introduction,
                           String address,String school, String society,String social,String socialNumber);

    /**
     * 更新用户信息
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
    @Update("update student_message set nickName=#{nickName},avatar=#{avatar},sex=#{sex},name=#{name},introduction=#{introduction}," +
            "address=#{address},school=#{school},society=#{society},social=#{social},socialNumber=#{socialNumber} where userName=#{userName}")
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