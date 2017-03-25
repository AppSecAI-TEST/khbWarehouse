/**
 * @author 陈大涛
 * 2016-6-7上午9:51:24
 */
package com.yeepay.g3.core.activity.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 陈大涛
 * 2016-6-7上午9:51:24
 */
public class DateFormatUtil {

	public static String  dateFormat( String pattern,Date date){
		return  new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 获取当天0点时间
	 * @return
	 */
	public static Date currentDateStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return cal.getTime();
	}
	
	/**
	 * 获取当天最后时间
	 * @return
	 */
	public static Date currentDateEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		return cal.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(DateFormatUtil.currentDateStart());
		System.out.println(DateFormatUtil.currentDateEnd());
	}
}
