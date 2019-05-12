package com.cs.demo.config;

import com.cs.demo.proeperties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/8 9:02
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {

}