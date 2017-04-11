/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.boss.activity.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author：tong.yue
 * @since：2015年5月19日 下午12:56:26
 * @version:
 */
public class DataFormater {

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param _patternMode
	 * @return
	 */
	public String formatDate(Object date, String _patternMode) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(_patternMode);
		return format.format(date);
	}

	/**
	 * 获取日期差
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Long getDifferDays(Date date) throws ParseException {
		return getDifferDays(new Date(), date);
	}

	/**
	 * 获取日期差
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static Long getDifferDays(Date date1, Date date2) throws ParseException {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = myFormatter.parse(myFormatter.format(date1));
		Date mydate = myFormatter.parse(myFormatter.format(date2));
		return (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
	}

}
