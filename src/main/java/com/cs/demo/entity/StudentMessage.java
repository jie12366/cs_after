package com.cs.demo.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/22 9:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_message")
public class StudentMessage {

    /**
     * 外键 主键 用户名
     */
    @Column(name = "userName",type = MySqlTypeConstant.VARCHAR,isKey = true)
    private String  userName;

    /**
     * 用户昵称
     */
    @Column(name = "nickName",type = MySqlTypeConstant.VARCHAR,length = 40)
    private String nickName;

    /**
     * 用户头像
     */
    @Column(name = "avatar",type = MySqlTypeConstant.VARCHAR)
    private String avatar;

    /**
     * 用户性别
     */
    @Column(name = "sex",type = MySqlTypeConstant.VARCHAR,length = 10)
    private String sex;

    /**
     * 真实姓名
     */
    @Column(name = "name",type = MySqlTypeConstant.VARCHAR,length = 80)
    private String name;

    /**
     * 个人简介
     */
    @Column(name = "introduction",type = MySqlTypeConstant.TEXT)
    private String introduction;

    /**
     * 所在地
     */
    @Column(name = "address",type = MySqlTypeConstant.VARCHAR,length = 80)
    private String address;

    /**
     * 所在学校
     */
    @Column(name = "school",type = MySqlTypeConstant.VARCHAR,length = 60)
    private String school;

    /**
     * 所在社团
     */
    @Column(name = "society",type = MySqlTypeConstant.VARCHAR,length = 60)
    private String society;

    /**
     * 联系人社交（QQ或微信）
     */
    @Column(name = "social",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String social;

    /**
     * 联系人社交账号
     */
    @Column(name = "socialNumber",type = MySqlTypeConstant.VARCHAR,length = 30)
    private String socialNumber;
}