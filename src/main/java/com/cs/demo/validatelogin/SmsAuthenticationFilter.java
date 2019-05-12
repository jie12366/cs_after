package com.cs.demo.validatelogin;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/6 18:09
 */
/**
 * 短信登录的过滤器
 * @author HZK
 * @date 2018年7月28日 下午10:22:39
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String IMOOC_FORM_MOBILE_KEY = "phone";
    private String mobileParameter = IMOOC_FORM_MOBILE_KEY;
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
        String mobile = obtainMobile(request);
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
     * 获取请求参数中的手机号
     * @param request
     * @return
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    /**
     * 将请求的信息设置在Token中
     * @param request
     * @param authRequest
     */
    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return mobileParameter;
    }
}