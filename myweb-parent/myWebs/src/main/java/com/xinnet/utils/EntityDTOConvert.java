package com.xinnet.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 转换属性的工具
 * @author hongbin.kang
 * @date 2017年3月25日上午10:22:29
 */
public class EntityDTOConvert {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityDTOConvert.class);
	/**
	 * 转换为目标类
	 * @param src
	 * @param t
	 * @return
	 */
	public static  <T> T toTarget(Object src, Class<T> t) {
		if (src == null) {
			return null;
		}
		try {
			T target = t.newInstance();
			BeanUtils.copyProperties(target,src);
			return target;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("转换出错");
			return null;
		}
	}
	/**
	 * 转化为目标类列表
	 * @param srcList
	 * @param t
	 * @return
	 */
	public static <T> List<T> toTragetList(List<?> srcList, Class<T> t) {
		List<T> targetList = new ArrayList<T>();
		if(srcList != null && !srcList.isEmpty()){
			for(Object src:srcList){
				targetList.add(toTarget(src, t));
			}
		}
		return targetList;
	}
	

}
