package com.cs.demo.compent;

import com.cs.demo.entity.ImageCode;
import com.cs.demo.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/7 23:05
 */
@Component
public class SpringAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    UserDetailServiceImpl userDetailService;
    private static final String KEY = "code";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class,authentication,"非验证类型");
        String userName = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailService.loadUserByUsername(userName);
        //如果密码不相等，抛出错误
        if (!password.equals(userDetails.getPassword())){
            throw  new UsernameNotFoundException("密码错误");
        }

        return new UsernamePasswordAuthenticationToken(userName,password,userDetails.getAuthorities());
    }

    public void Code(HttpServletRequest request) throws AuthenticationException {
        HttpSession session = request.getSession();
        ImageCode code = (ImageCode)session.getAttribute(KEY);
        String codeRequest = request.getParameter("imageCode");
        if(org.apache.commons.lang3.StringUtils.isBlank(codeRequest)){
            throw  new UsernameNotFoundException("验证码为空");
        }
        if(code == null){
            throw  new UsernameNotFoundException("验证码不存在");
        }
        if (code.isExpired()){
            session.removeAttribute(KEY);
            throw  new UsernameNotFoundException("验证码过期");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}