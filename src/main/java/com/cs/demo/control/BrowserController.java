package com.cs.demo.control;

import com.cs.demo.proeperties.SecurityProperties;
import com.cs.demo.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/7 22:34
 */
@RestController
public class BrowserController {

    private static final Logger log = LoggerFactory.getLogger(BrowserController.class);
    private RequestCache cache = new HttpSessionRequestCache();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public JsonResult requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = cache.getRequest(request,response);

        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是：" + targetUrl);
            String ends = ".html";
            if(StringUtils.endsWithIgnoreCase(targetUrl,ends)){
                return JsonResult.errorLogin("请引导用户到登录页");
            }
        }
        return JsonResult.errorLogin("请引导用户到登录页");
    }

    @GetMapping("/error")
    public void getError(){

    }
}