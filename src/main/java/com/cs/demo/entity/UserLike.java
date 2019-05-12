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
 * @date 2019/5/9 14:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_like")
public class UserLike {

    /**
     * 主键，用户id
     */
    @Column(name = "userId",type = MySqlTypeConstant.INT,length = 10)
    private int userId;

    /**
     * 点赞者的昵称
     */
    @Column(name = "nickName",type = MySqlTypeConstant.VARCHAR)
    private String nickName;
}