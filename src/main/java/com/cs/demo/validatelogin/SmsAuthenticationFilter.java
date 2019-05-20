package com.cs.demo.validatelogin;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/6 18:09
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private boolean postOnly = true;

    public SmsAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/phone", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String mobile = request.getParameter("phone");

        if (mobile == null) {
            mobile = "";
        }
        mobile = mobile.trim();
        SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile);

        // 将请求的信息设置在Token中
        setDetails(request, authRequest);
        //拿着Token调用AuthenticationManager
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 将请求的信息设置在Token中
     * @param request
     * @param authRequest
     */
    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}