package com.yeepay.g3.app.lmweChat.utils;

import java.math.BigDecimal;

/**
 * 数字格式化工具
 * 
 * @author dongdong.tang
 * 
 */
public class NumberFormatUtils {
	
	/**
	 * 获取字符串数据小数点精度
	 * @param num 传入的数据
	 * @param sacle 精度
	 * @return
	 */
	public static String StringToString(String num, Integer sacle) {
		if (num != null) {
			BigDecimal bd = new BigDecimal(num);
			bd = bd.setScale(sacle, BigDecimal.ROUND_HALF_UP); //小数点后两位，四舍五入
			return bd.toString();
		}
		return num;
	}
	
	/**
	 * 字符串转数字
	 * @param num 传入的数据
	 * @param sacle 精度
	 * @return
	 */
	public static BigDecimal StringToDecimal(String num, Integer sacle) {
		if (num != null) {
			BigDecimal bd = new BigDecimal(num);
			bd = bd.setScale(sacle, BigDecimal.ROUND_HALF_UP); //小数点后两位，四舍五入
			return bd;
		}
		return null;
	}
	
	/**
	 * 数字转字符串
	 * @param num 传入的数据
	 * @param sacle 精度
	 * @return
	 */
	public static String DecimalToString(BigDecimal num, Integer sacle) {
		if (num != null) {
			num = num.setScale(sacle, BigDecimal.ROUND_HALF_UP); //小数点后两位，四舍五入
			return num.toString();
		}
		return null;
	}
	
	/**
	 * 字符串缺省
	 * @param num 传入的字符串
	 * @param beforeNum 开始需要显示的数字位数
	 * @param afterNum 结束需要显示的数字位数
	 * @return
	 */
	public static String getBlurNoString(String num,String reg, Integer beforeNum, Integer afterNum) {
		if (beforeNum + afterNum > num.length()) { //超过要转换的字符串，无返回值
			return null;
		}
		StringBuffer sb = new StringBuffer(num.substring(0, beforeNum));
		for (int i = 0; i < (num.length()-beforeNum-afterNum); i++) {
			sb.append(reg);
		}
		sb.append(num.substring((num.length()-afterNum), num.length()));
		return sb.toString();
	}
	
	/**
	 * BigDecimal转String
	 * @param amount
	 * @return
	 */
	public static String getStrFromDecimal(BigDecimal amount){
		if(amount == null){
			return "0.00";
		}
		BigDecimal num = amount.setScale(2, BigDecimal.ROUND_HALF_UP); //小数点后两位，四舍五入
		return num.toString();
	}
}