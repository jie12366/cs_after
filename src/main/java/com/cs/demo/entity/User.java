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
 * @date 2019/4/17 21:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Column(name = "id",type = MySqlTypeConstant.INT,isKey = true,isAutoIncrement = true)
    private int id;

    @Column(name = "userName",type = MySqlTypeConstant.VARCHAR,length = 20,isUnique = true)
    private String userName;

    @Column(name = "password",type = MySqlTypeConstant.VARCHAR,length = 80)
    private String password;

    @Column(name = "phone",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String phone;
}