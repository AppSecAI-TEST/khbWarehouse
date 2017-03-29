package com.xinnet.packagescanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;
  
/*@Component*/ //注释掉，以后研究
public class HessianServiceScanner implements BeanFactoryPostProcessor {  
	
	private static final Logger logger = LoggerFactory.getLogger(HessianServiceScanner.class);
	
	private String packageName;
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}  
  
    public void postProcessBeanFactory(  
            ConfigurableListableBeanFactory beanFactory) throws BeansException {  
       
    	PackageScanner packageScanner = new PackageScanner(packageName);
    	List<String> beanNames = new ArrayList<String>();
		try {
			beanNames = packageScanner.getFullyQualifiedClassNameList();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        for (String beanName : beanNames) {  
            String className = beanFactory.getBeanDefinition(beanName)  
                    .getBeanClassName();  
            Class<?> clasz = null;  
            try {  
                clasz = Class.forName(beanName);  
            } catch (ClassNotFoundException e) {  
                throw new BeanInitializationException(e.getMessage(), e);  
            }
            //判断是否为接口
            if(clasz.isInterface()) {
            	String simpleName = StringUtil.toLowerCaseFirstOne(clasz.getSimpleName());
            	String hessianServiceBeanName = "/" + simpleName;  
            	String service = simpleName + "Impl";  
            	
            	BeanDefinitionBuilder builder = BeanDefinitionBuilder  
            			.rootBeanDefinition(HessianServiceExporter.class);  
            	builder.addPropertyReference("service", service);  
            	builder.addPropertyValue("serviceInterface",  
            			beanName);  
            	((BeanDefinitionRegistry) beanFactory).registerBeanDefinition(  
            			hessianServiceBeanName, builder.getBeanDefinition());
            	logger.info("service={}", hessianServiceBeanName);
            }
        }  
    }
}
