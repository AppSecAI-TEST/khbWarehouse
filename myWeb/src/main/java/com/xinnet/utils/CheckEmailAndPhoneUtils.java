package com.xinnet.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 参数校验
 * @author hongbin.kang
 * @date 2016年10月9日下午3:46:57
 */
public class CheckEmailAndPhoneUtils {
	private static final Logger logger = LoggerFactory.getLogger(CheckEmailAndPhoneUtils.class);
	
	/**
	  * 验证邮箱地址是否正确
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
