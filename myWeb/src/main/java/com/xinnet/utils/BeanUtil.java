package com.xinnet.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * Bean Operation Utils
 * </p>
 *
 * @author IceWee
 * @see org.springframework.beans.BeanUtils
 */
public class BeanUtil {

	private BeanUtil() {
		super();
	}

	/**
	 * <p>
	 * Copy the property values of the given source bean into the target bean.
	 * </p>
	 * 
	 * <p>
	 * Note: The source and target classes do not have to match or even be derived from each other, as long as the properties match. Any bean
	 * properties that the source bean exposes but the target bean does not will silently be ignored.
	 * </p>
	 * 
	 * <p>
	 * This is just a convenience method. For more complex transfer needs, consider using a full BeanWrapper.
	 * </p>
	 * 
	 * @param source the source bean
	 * @param target the target bean
	 * @see org.springframework.beans.BeanWrapper
	 */
	@SuppressWarnings("unchecked")
	public static void copyProperties(Object source, Object target) {
		if (source != null && target != null) {
			if (source instanceof Map && target instanceof Map) {
				// source and target all are maps
				copyKeyValues((Map<String, Object>) source, (Map<String, Object>) target);
			} else if (source instanceof Map && !(target instanceof Map)) {
				// source is map and target is javabean
				map2bean((Map<String, Object>) source, target);
			} else if (!(source instanceof Map) && target instanceof Map) {
				// source is javabean and target is map
				bean2map(source, (Map<String, Object>) target);
			} else {
				// source and target all are javabeans
				BeanUtils.copyProperties(source, target);
			}
		}
	}

	/**
	 * <p>
	 * Copy the key/values of the given source map into the target map.
	 * </p>
	 * 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void copyKeyValues(Map<String, Object> source, Map<String, Object> target) {
		if (source != null && target != null) {
			target.putAll(source);
		}
	}

	/**
	 * <p>
	 * Copy the key/values of the given source map into the target bean.
	 * </p>
	 * 
	 * @param source the source map
	 * @param target the target bean
	 * @throws IllegalAccessException if the caller does not have access to the property accessor method
	 */
	private static void map2bean(Map<String, Object> source, Object target) {
		if (source != null && target != null) {
			try {
				org.apache.commons.beanutils.BeanUtils.populate(target, source);
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * <p>
	 * Copy the property/values of the given source bean into the target map.
	 * </p>
	 * 
	 * @param source the source bean
	 * @param target the target map
	 */
	private static void bean2map(Object source, Map<String, Object> target) {
		if (source != null && target != null) {
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				Method getMethod;
				String key;
				Object value;
				for (PropertyDescriptor property : propertyDescriptors) {
					key = property.getName();
					if (!StringUtils.equals("class", key)) {
						getMethod = property.getReadMethod();
						value = getMethod.invoke(source);
						target.put(key, value);
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
