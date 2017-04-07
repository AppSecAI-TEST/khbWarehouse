package com.xinnet.utils;

import java.lang.reflect.Field;
import java.util.List;
import com.xinnet.annotation.NotEmpty;

/**
 * 参数校验
 * @author hongbin.kang
 * @date 2016年10月9日下午3:46:57
 */
public class CheckParamUtils {

	/**
	 * 对校验实体属性的非空校验
	 * 
	 * @param obj
	 *            实体
	 * @param clazz
	 *            类
	 */
	public static void isEmpty(Object obj) {
		// 非NULL校验
		if (obj == null)
			throw new IllegalArgumentException("参数非NULL");

		// 参数非空校验
		Class<?> clazz = obj.getClass();
		do {
			checkClassEmpty(obj, clazz);
			clazz = clazz.getSuperclass();
		} while (clazz != null);

	}

	/**
	 * 对当前类的属性进行非空校验
	 * 
	 * @param obj
	 * @param clazz
	 */
	private static void checkClassEmpty(Object obj, Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
			if (notEmpty != null) {
				boolean value = notEmpty.value();
				if (value) {// 需要进行非空校验
					checkAttrValueNotEmpty(obj, field);
				}
			}
		}
	}

	/**
	 * 针对具体属性进行非空校验
	 * 
	 * @param obj
	 * @param field
	 */
	@SuppressWarnings("rawtypes")
	private static void checkAttrValueNotEmpty(Object obj, Field field) {
		field.setAccessible(true);
		Object val = null;
		try {
			val = field.get(obj);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("属性获取异常", e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("属性获取异常", e);
		}
		String type = field.getType().toString();
		if (type.endsWith("String")) {
			String vv = (String) val;
			if (vv == null || vv.trim().length() <= 0) {
				throw new IllegalArgumentException(getAttrName(field) + " 不能为空！");
			}
			
			NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
			int maxLength = notEmpty.maxLength();
			if(maxLength > 0 && vv.trim().length() > maxLength){
				throw new IllegalArgumentException(getAttrName(field) + " 字段超长！");
			}
		}else if(type.endsWith("List")){
			List vv = (List) val;
			if(vv == null || vv.size() <= 0){
				throw new IllegalArgumentException(getAttrName(field) + " 不能为空！");
			}
			NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
			int maxLength = notEmpty.maxLength();
			if(maxLength > 0 && vv.size() != maxLength){
				throw new IllegalArgumentException(getAttrName(field) + " 长度不正确！");
			}
		}else {
			if (val == null) {
				throw new IllegalArgumentException(getAttrName(field) + " 不能为空！");
			}
		}
	}

	/**
	 * 获取属性名称
	 * 
	 * @param field
	 *            属性
	 * @return String 属性名
	 */
	private static String getAttrName(Field field) {
		NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);

		String name = notEmpty.name();
		if (name == null || name.trim().length() == 0) {
			return field.getName();
		}
//		return name + "(" + field.getName() + ")";
		return name;
	}

}
