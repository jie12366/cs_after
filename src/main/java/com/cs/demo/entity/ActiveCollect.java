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
 * @date 2019/5/9 20:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "active_collect")
public class ActiveCollect {

    /**
     * 主键，自增id
     */
    @Column(name = "id",type = MySqlTypeConstant.INT,length = 5,isKey = true,isAutoIncrement = true)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "userName",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String userName;

    /**
     * 活动id
     */
    @Column(name = "activeId",type = MySqlTypeConstant.INT,length = 10)
    private int activeId;
}