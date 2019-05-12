package com.cs.demo.proeperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/8 8:58
 */
@ConfigurationProperties(prefix = "jie.security")
public class SecurityProperties {

    BrowserProperties properties = new BrowserProperties();

    public BrowserProperties getProperties() {
        return properties;
    }

    public void setProperties(BrowserProperties properties) {
        this.properties = properties;
    }
}