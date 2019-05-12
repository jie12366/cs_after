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
 * @date 2019/4/29 8:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "active")
public class Active {

    /**
     * 主键，自增id
     */
    @Column(name = "activeId",type = MySqlTypeConstant.INT,length = 10,isKey = true,isAutoIncrement = true)
    private int activeId;

    /**
     * 活动标题
     */
    @Column(name = "title",type = MySqlTypeConstant.VARCHAR,length = 80)
    private String title;

    /**
     * 活动内容
     */
    @Column(name = "content",type = MySqlTypeConstant.TEXT)
    private String content;

    /**
     * 活动金额范围
     */
    @Column(name = "money",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String money;

    /**
     * 活动类别
     */
    @Column(name = "category",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String category;

    /**
     * 活动时间
     */
    @Column(name = "activeTime",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String activeTime;

    /**
     * 活动地点
     */
    @Column(name = "address",type = MySqlTypeConstant.VARCHAR)
    private String address;

    /**
     * 上传的附件
     */
    @Column(name = "annex",type = MySqlTypeConstant.VARCHAR)
    private String annex;

    /**
     * 联系人姓名
     */
    @Column(name = "name",type = MySqlTypeConstant.VARCHAR,length = 30)
    private String name;

    /**
     * 联系人电话
     */
    @Column(name = "phone",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String phone;

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

    /**
     * 是否公开联系人信息
     * 1为公开，0为不公开
     */
    @Column(name = "isPublic",type = MySqlTypeConstant.INT,length = 10)
    private int isPublic;

    /**
     * 创建时间
     */
    @Column(name = "createTime",type = MySqlTypeConstant.VARCHAR)
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "updateTime",type = MySqlTypeConstant.VARCHAR)
    private String updateTime;
}