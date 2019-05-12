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
 * @date 2019/5/10 12:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "active_picture")
public class ActivePicture {

    /**
     * 活动id
     */
    @Column(name = "activeId",type = MySqlTypeConstant.INT,length = 10)
    private int activeId;

    /**
     * 活动图片
     */
    @Column(name = "picture",type = MySqlTypeConstant.VARCHAR)
    private String picture;
}