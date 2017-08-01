package com.xinnet.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
 




import org.springframework.stereotype.Component;

import java.util.Map;
 
/**
 * Spring bean获取工具类
 * @author hongbin.kang
 * @date 2017年7月31日下午5:03:08
 */
@SuppressWarnings({"unchecked","static-access","rawtypes"})
@Component
public class SpringContext implements ApplicationContextAware {
 
    private static ApplicationContext applicationContext;
 
 
    
	@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
 
    /**
     * 获取applicationContext对象
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
 
    /**
     * 根据bean的id来查找对象
     * @param id
     * @return
     */
    public static Object getBeanById(String id){
        return applicationContext.getBean(id);
    }
 
    /**
     * 根据bean的class来查找对象
     * @param c
     * @return
     */
    
	public static Object getBeanByClass(Class c){
        return applicationContext.getBean(c);
    }
 
    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param c
     * @return
     */
    public static Map getBeansByClass(Class c){
        return applicationContext.getBeansOfType(c);
    }
    /**
     * 根据bean的Name来查找对象
     * @param name
     * @return
     */
    public static Object getBeanByName(String name){
        return applicationContext.getBean(name);
    }
}
