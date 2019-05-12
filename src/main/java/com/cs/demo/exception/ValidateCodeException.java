package com.cs.demo.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/8 14:09
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -6100356355374114882L;

    public ValidateCodeException(String msg){
        super(msg);
    }
}