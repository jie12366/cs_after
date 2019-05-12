package com.cs.demo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/6 20:55
 */
@Data
public class SmsCode {

    private String code;
    private LocalDateTime expireTime;

    /**
     * @author 熊义杰 www.xyj123.xyz
     * @description 传入过期具体时间来构造
     * @date 2019/4/8 12:45
     * @param [code, expireIn]
     * @return
     **/
    public SmsCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 判断是否过期
     * @return
     */
    public Boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}