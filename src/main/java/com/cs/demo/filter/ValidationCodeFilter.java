package com.cs.demo.filter;

import com.cs.demo.entity.ImageCode;
import com.cs.demo.entity.SmsCode;
import com.cs.demo.exception.ValidateCodeException;
import com.cs.demo.service.RedisService;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/17 12:48
 */
@Data
public class ValidationCodeFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler;
    private static final String KEY = "code";

    @Resource
    RedisTemplate redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/authentication/login", request.getRequestURI())
                && StringUtils.equalsIgnoreCase("post", request.getMethod())) {
            try {
                validate1(request);
            }
            catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                // 不继续执行
                return;
            }
        }
        if (StringUtils.equals("/authentication/phone", request.getRequestURI())
                && StringUtils.equalsIgnoreCase("post", request.getMethod())) {
            try {
                validate2(request);
            }
            catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                // 不继续执行
                return;
            }
        }
        // 继续执行下一步
        filterChain.doFilter(request, response);
    }
    private void validate1(HttpServletRequest request) throws ServletRequestBindingException {
        ImageCode code = (ImageCode)request.getServletContext().getAttribute(KEY);

        if (code == null){
            throw new ValidateCodeException("请发送验证码");
        }
        String imageCode = request.getParameter("imageCode");
        if (StringUtils.isBlank(imageCode)) {
            throw new ValidateCodeException("验证码为空或不存在");
        }
        if (code.isExpired()){
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(imageCode, code.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }
        request.getServletContext().removeAttribute(KEY);
    }

    private void validate2(HttpServletRequest request) {

        SmsCode code = (SmsCode) request.getSession().getAttribute("smsCode");
        if (code == null){
            throw new ValidateCodeException("请发送验证码");
        }
        String smsCode = request.getParameter("smsCode");
        if (StringUtils.isBlank(smsCode)) {
            throw new ValidateCodeException("验证码为空");
        }
        if (code.isExpired()) {
            throw new ValidateCodeException("验证码过期");
        }
        if (!StringUtils.equalsIgnoreCase(smsCode, code.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }
    }
}