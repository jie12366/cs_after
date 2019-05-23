package com.cs.demo.cache;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/23 17:48
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过类（.class）获取到bean
     * @param clazz 传入的类
     * @param <T> 类的泛型
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过类名获取到bean
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String  name){
        return (T)applicationContext.getBean(name);
    }
}