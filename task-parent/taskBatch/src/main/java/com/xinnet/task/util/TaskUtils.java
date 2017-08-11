package com.xinnet.task.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.xinnet.task.domain.ScheduleJob;

public class TaskUtils {
	public final static Logger log = Logger.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(ScheduleJob scheduleJob) {
		Object object = null;
		Class<?> clazz = null;
		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
			object = SpringUtils.getBean(scheduleJob.getSpringId());
		} else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (object == null) {
			log.error("名称 = [" + scheduleJob.getJobName() + "]---------------未找到该class，请检查是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		String params = null;
		try {
			//判断是否需要参数
			params = scheduleJob.getParams();
			if(StringUtils.isEmpty(params)) {
				method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
			} else {
				method = clazz.getDeclaredMethod(scheduleJob.getMethodName(),String.class);
			}
			
		} catch (NoSuchMethodException e) {
			log.error("名称 = [" + scheduleJob.getJobName() + "]---------------未找到该方法，方法名设置错误！！！");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (method != null) {
			try {
				if(StringUtils.isEmpty(params)) {
					method.invoke(object);
				} else {
					method.invoke(object,params);
				}
				log.info("任务名称 = [" + scheduleJob.getJobName() + "]----------执行成功，时间--["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
