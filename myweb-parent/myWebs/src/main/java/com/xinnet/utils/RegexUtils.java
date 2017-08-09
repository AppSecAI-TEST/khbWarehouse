package com.xinnet.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class RegexUtils {
	
	private static Logger logger = Logger.getLogger(RegexUtils.class);
	
	/**
	 * 汉字或者英文组成
	 * @author hongbin.kang
	 * @date 2017年5月8日 上午10:05:44
	 * @param str
	 * @return
	 */
	public static boolean isChineseOrEnglish(String str) {
		String regex="^[a-zA-Z\u4E00-\u9FA5]+$";
		return str.matches(regex);
	}
	
	/**
	 * 纯数字
	 * @author hongbin.kang
	 * @date 2017年5月8日 上午10:06:21
	 * @param str
	 * @return
	 */
	public static boolean isLetterDigit(String str) {
		String regex = "^[0-9]+$";
		return str.matches(regex);
	}
	
	/**
	 * 汉字组成
	 * @author hongbin.kang
	 * @date 2017年5月8日 上午10:06:08
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		String regex="^[\u4E00-\u9FA5]+$";
		return str.matches(regex);
	}
	
	/**
	 * 英文组成
	 * @author hongbin.kang
	 * @date 2017年5月8日 上午10:06:59
	 * @param str
	 * @return
	 */
	public static boolean isEnglish(String str) {
		String regex="^[a-zA-Z]+$";
		return str.matches(regex);
	}
	
	/**
	 * 验证邮箱地址是否正确
	 * @author hongbin.kang
	 * @date 2017年5月8日 上午10:13:19
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email){
		boolean flag = false;
		if(StringUtils.isEmpty(email)) {
			return flag;
		}
		try{
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		}catch(Exception e){
			logger.error("验证邮箱地址错误", e);
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 验证手机号码
	 * @author hongbin.kang
	 * @date 2017年5月8日 上午10:13:28
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles){
		boolean flag = false;
		if(StringUtils.isEmpty(mobiles)) {
			return flag;
		}
		try{
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		}catch(Exception e){
			logger.error("验证手机号码错误", e);
			flag = false;
		}
		return flag;
	}
	
}
