package com.yeepay.g3.app.lmweChat.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

/**
 * 日期工具类
 * 
 * @author Hz.Liu
 * @date 2016年8月17日 上午9:59:07
 *
 */
public class DateUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(DateUtils.class);

	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
	public static final String MMDD = "MM-dd";
	
	public static String getDateByFormat(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			logger.error(
					"[DateUtils][getDateByFormat] 日期转字符串异常.exception="
							+ e.getMessage(), e);
		}
		return "--";
	}
	/**
	 * 时间转换成基金工作日时间
	 * @author 陈大涛
	 * 2016-11-16下午8:00:00
	 */
	public static Date formatFundDate(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		Calendar fundNow = Calendar.getInstance();
		fundNow.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 15, 0,0);
		if(now.compareTo(fundNow)>0){//时间超过下午3点
			now.add(Calendar.DATE, 1);
		}
		return now.getTime();
	}
}
