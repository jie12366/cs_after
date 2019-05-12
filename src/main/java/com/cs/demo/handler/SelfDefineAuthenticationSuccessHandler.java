package com.cs.demo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/17 12:41
 */
@Component
public class SelfDefineAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 序列化反序列化工具
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 登陆成功
     *
     * @param request
     * @param response
     * @param authentication 封装了认证信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登陆成功了");
        response.setContentType("application/json;charset=utf-8");
        String value= objectMapper.writeValueAsString(authentication.getPrincipal() + "登录成功");
        logger.info(value);
        response.getWriter().write(value);
    }
}