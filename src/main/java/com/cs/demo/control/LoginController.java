package com.cs.demo.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/8 9:32
 */

@Controller
public class LoginController {

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}