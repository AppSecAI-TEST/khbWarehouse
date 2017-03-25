/**
 * 
 */
package com.yeepay.g3.core.activity.utils;

import java.util.ArrayList;
import java.util.List;

import com.yeepay.g3.utils.common.BeanUtils;
import com.yeepay.g3.utils.common.exception.YeepayRuntimeException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

/**
 * @Description 
 * @author zhenping.zhou
 * @CreateTime 2015年12月7日 上午11:52:04
 * @version 1.0
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
			BeanUtils.copyProperties(src, target);
			return target;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new YeepayRuntimeException("{0} failed convert to {1}",
					src.getClass(), t);
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
