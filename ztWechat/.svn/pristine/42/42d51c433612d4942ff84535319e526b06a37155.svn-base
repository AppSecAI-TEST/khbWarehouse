package com.yeepay.g3.app.lmweChat.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

public class RegexMatchesUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(RegexMatchesUtils.class);

	/**
	 * 校验身份证
	 * @author 陈大涛
	 * 2016-10-11下午6:52:56
	 */
	public  static boolean regexIdCard(String idCard){
		if(StringUtils.isEmpty(idCard)){
			return false;
		}
//		String regex="/^\\d{6}(18|19|20)?\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X)?$/";
//		String regex="(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
		String regex="^\\d{6}(18|19|20)?\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X)?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher idCardMatcher = pattern.matcher(idCard);
		if(!idCardMatcher.matches()){//校验身份证号格式
			return false;
		}else{
			Map<String,String> cityMap = new HashMap<String,String>();
			cityMap.put("11", "北京");cityMap.put("12", "天津");cityMap.put("13", "河北");
			cityMap.put("14", "山西");cityMap.put("15", "内蒙古");cityMap.put("21", "辽宁");
			cityMap.put("22", "吉林");cityMap.put("23", "黑龙江");cityMap.put("31", "上海");
			cityMap.put("32", "江苏");cityMap.put("33", "浙江");cityMap.put("34", "安徽");
			cityMap.put("35", "福建");cityMap.put("36", "江西");cityMap.put("37", "山东");
			cityMap.put("41", "河南");cityMap.put("42", "湖北");cityMap.put("43", "湖南");
			cityMap.put("44", "广东");cityMap.put("45", "广西");cityMap.put("46", "海南");
			cityMap.put("51", "重庆");cityMap.put("52", "四川");cityMap.put("53", "贵州");
			cityMap.put("54", "云南");cityMap.put("55", "西藏");cityMap.put("61", "陕西");
			cityMap.put("62", "甘肃");cityMap.put("63", "青海");cityMap.put("64", "宁夏");
			cityMap.put("65", "新疆");cityMap.put("71", "台湾");cityMap.put("81", "香港");
			cityMap.put("82", "澳门");cityMap.put("92", "国外");
			if(cityMap.get(idCard.substring(0, 2))==null){//校验地址编码
				return false;
			}else{
				if(idCard.length()==18){ //18位身份证需要验证最后一位校验位
					String[] idCardArray=idCard.split("");
					Integer[] factor={ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
					String[] parity = { "1", "0","X","9","8","7","6","5","4","3","2"};
					int sum=0,ai=0,wi=0;
			          for (int i = 0; i < 17; i++){
			              ai = Integer.valueOf(idCardArray[i+1]);
			              wi = factor[i];
			              sum += ai * wi;
			          }
			          if(!idCardArray[18].equals(parity[sum % 11] )){
			              //校验位错误";
			              return false;
			          } 
				}
			}
		}
		 return true;
	}
	/**
	 * 交易密码正则验证
	 * @author 陈大涛
	 * 2016-10-11下午8:04:14
	 */
	public static boolean regexTradePWD(String tradePwd){
		if(StringUtils.isEmpty(tradePwd)){
			return false;
		}
		String regex="(?!([a-zA-Z]+|\\d+)$)[a-zA-Z\\d]{8,20}";
		Pattern pattern = Pattern.compile(regex);
		if(pattern.matcher(tradePwd).matches()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 校验是否是中文
	 * @author 陈大涛
	 * 2016-10-11下午8:21:30
	 */
	public static boolean regexIsChinese(String param){
		if(StringUtils.isEmpty(param)){
			return false;
		}
		String regex="^[\u4e00-\u9fa5]*$";
		Pattern pattern = Pattern.compile(regex);
		if(pattern.matcher(param).matches()){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
//		String idCard="342221199207036515";
//		System.out.println( regexIdCard(idCard));
		String tradePwd="11111222f";
		System.out.println(regexTradePWD(tradePwd));
		String param="在";
		System.out.println(regexIsChinese(param));
	}
}
