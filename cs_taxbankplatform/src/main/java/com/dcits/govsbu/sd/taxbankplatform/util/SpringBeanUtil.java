package com.dcits.govsbu.sd.taxbankplatform.util;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//
//public final class SpringBeanUtil implements ApplicationContextAware {
//
//    private static ApplicationContext ctx;
//
//    public static Object getBean(String id) {
//        if (ctx == null) {
//            throw new NullPointerException("ApplicationContext is null");
//        }
//        return ctx.getBean(id);
//    }
//
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        ctx = applicationContext;
//    }
//
//}

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class SpringBeanUtil extends SpringBeanAutowiringSupport {

    /**
     * 自动装配注解会让Spring通过类型匹配为beanFactory注入示例
     */
    @Autowired
    private BeanFactory beanFactory;

    private SpringBeanUtil() {
    }

    private static SpringBeanUtil instance;

    static {
        instance = new SpringBeanUtil();
    }

    /**
     * 实例方法
     * 使用的时候先通过getInstance方法获取实例
     * 
     * @param beanId
     * @return
     */
    public Object getBeanById(String beanId) {
        return beanFactory.getBean(beanId);
    }

    public static SpringBeanUtil getInstance() {
        return instance;
    }
}