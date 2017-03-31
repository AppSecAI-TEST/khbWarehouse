package com.xinnet.core.BeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;

import com.xinnet.core.annotation.HessianService;
  
@Component  
public class HessianServiceScanner implements BeanFactoryPostProcessor {  
  
    public void postProcessBeanFactory(  
            ConfigurableListableBeanFactory beanFactory) throws BeansException {  
        String[] beanNames = beanFactory  
                .getBeanNamesForAnnotation(HessianService.class);  
        for (String beanName : beanNames) {  
            String className = beanFactory.getBeanDefinition(beanName)  
                    .getBeanClassName();  
            Class<?> clasz = null;  
            try {  
                clasz = Class.forName(className);  
            } catch (ClassNotFoundException e) {  
                throw new BeanInitializationException(e.getMessage(), e);  
            }  
            String hessianServiceBeanName = "/" + beanName.replace("Impl", "");  
  
            BeanDefinitionBuilder builder = BeanDefinitionBuilder  
                    .rootBeanDefinition(HessianServiceExporter.class);  
            builder.addPropertyReference("service", beanName);
            String serviceInterface = clasz.getInterfaces()[0].getName();
            builder.addPropertyValue("serviceInterface",serviceInterface);  
            ((BeanDefinitionRegistry) beanFactory).registerBeanDefinition(  
                    hessianServiceBeanName, builder.getBeanDefinition());  
        }  
    }  
  
}
