package com.cs.demo.proeperties;

import lombok.Data;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/8 8:54
 */
@Data
public class BrowserProperties {

    private String loginPage  = "/login";

    private int rememberMeSeconds = 3600;
}