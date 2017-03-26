package com.xinnet.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * 日期工具类
 * @author hongbin.kang
 * @date 2016年9月5日上午10:25:41
 */
public class DateUtil {
	
	private static final Logger logger = Logger.getLogger(DateUtil.class);

	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
	public static final String MMDD = "MM-dd";
	
	/**
	 * 日期格式化为字符串
	 * @author hongbin.kang
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
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
	 * 将字符串转换为日期类型
	 * @author hongbin.kang
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str) {  
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = null;  
		try {  
			date = format.parse(str);   
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  
//		date = java.sql.Date.valueOf(str);  
		
		return date;  
	} 
	
	/**
	 * 将字符串转换为日期类型
	 * @author hongbin.kang
	 * @param str
	 * @param formate
	 * @return
	 */
	public static Date strToDate(String str, String formate) {  
		DateFormat format = new SimpleDateFormat(formate);  
		Date date = null;  
		try {  
			date = format.parse(str);   
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  
//		date = java.sql.Date.valueOf(str);  
		
		return date;  
	} 
	
	/**
	 * 操作日期月份
	 * @author hongbin.kang
	 * @date 2016年8月17日 下午5:11:06
	 * @param num
	 * @return
	 * @throws ParseException
	 */
	public static Date addMonths(Date oriDate, int num) throws ParseException {
		Calendar cal  = Calendar.getInstance();
		cal.setTime(oriDate);
		cal.add(Calendar.MONTH, num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date=sdf.parse(sdf.format(cal.getTime())); 
		return date;
	}
	
	/**
	 * 操作日期天数
	 * @author hongbin.kang
	 * @date 2016年9月5日 上午10:28:03
	 * @param oriDate
	 * @param num
	 * @return
	 * @throws ParseException
	 */
	public static Date addDays(Date oriDate, int num) throws ParseException {
		Calendar cal  = Calendar.getInstance();
		cal.setTime(oriDate);
		cal.add(Calendar.DATE, num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date=sdf.parse(sdf.format(cal.getTime())); 
		return date;
	}
	
	/**
	 * 操作日期年份
	 * @author hongbin.kang
	 * @param oriDate
	 * @param num
	 * @return
	 * @throws ParseException
	 */
	public static Date addYears(Date oriDate, int num) throws ParseException {
		Calendar cal  = Calendar.getInstance();
		cal.setTime(oriDate);
		cal.add(Calendar.YEAR, num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date=sdf.parse(sdf.format(cal.getTime())); 
		return date;
	}
	
	/**比较指定时间在时间区间内
	 * @param left	时间左值
	 * @param right 时间右值
	 * @param middle 时间中间值
	 * @return
	 */
	public static boolean checkDateBetween(Date left, Date right, Date middle){
		if(null == left || null == right || null == middle){
			return false;
		}
		return (left.compareTo(middle) < 1) && (middle.compareTo(right) < 1);
	}
	
	/**
	 * 获取两个时间之间相差整数天
	 * @author hongbin.kang
	 * @param min 小日期
	 * @param max 大日期
	 * @return
	 */
	public static int getBetweenDays(Date min, Date max) {
	  Calendar d1 = Calendar.getInstance();
	  d1.setTime(min);
	  Calendar d2 = Calendar.getInstance();
	  d2.setTime(max);
	  int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);
	  int y2 = d2.get(Calendar.YEAR);
	  if (d1.get(Calendar.YEAR) != y2) {
	    do {
	      days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
	      d1.add(Calendar.YEAR, 1);
	    } while (d1.get(Calendar.YEAR) != y2);
	  }
	  return days;
	}

}
