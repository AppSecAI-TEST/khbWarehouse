package com.xinnet.core.BeanFactoryPostProcessor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数非空
 * @author hongbin.kang
 * @date 2016年10月9日下午3:45:50
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NotEmpty {
	
	/** 是否判空，默认不判 */
	boolean value() default true;

	/** 中文名称 */
	String name() default "";
	
	/** 最大长度，String有效。 */
	int maxLength() default 0;
}
