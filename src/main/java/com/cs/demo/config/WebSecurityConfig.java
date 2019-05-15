package com.cs.demo.config;

import com.cs.demo.filter.ValidationCodeFilter;
import com.cs.demo.handler.SelfDefineAuthenticationFailureHandler;
import com.cs.demo.handler.SelfDefineAuthenticationSuccessHandler;
import com.cs.demo.proeperties.SecurityProperties;
import com.cs.demo.service.UserDetailServiceImpl;
import com.cs.demo.validatelogin.SmsAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/7 22:30
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    SelfDefineAuthenticationSuccessHandler selfDefineAuthenticationSuccessHandler;
    @Autowired
    SelfDefineAuthenticationFailureHandler selfDefineAuthenticationFailureHandler;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService detailsService;

    @Autowired
    SmsAuthenticationConfig smsAuthenticationConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        ///第一次使用需要创建表 tokenRepository.setCreateTableOnStartup(true);
       return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidationCodeFilter validationCodeFilter = new ValidationCodeFilter();
        //设置错误失败处理器
        validationCodeFilter.setAuthenticationFailureHandler( selfDefineAuthenticationFailureHandler );

        http.
                addFilterBefore( validationCodeFilter, UsernamePasswordAuthenticationFilter.class )
                .formLogin()
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/authentication/**")
                    .successHandler(selfDefineAuthenticationSuccessHandler)
                    .failureHandler(selfDefineAuthenticationFailureHandler)
                    .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getProperties().getRememberMeSeconds())
                .userDetailsService(detailsService)
                .and()
                .authorizeRequests()
                    .antMatchers("/authentication/require","/user/**",
                        securityProperties.getProperties().getLoginPage()
                    ,"/getCode","/sendMsg","/active/**").permitAll()
                    .antMatchers("/student/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .csrf().disable()
                .apply(smsAuthenticationConfig);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring(). antMatchers("/doc.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/static/front/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder);
    }
}