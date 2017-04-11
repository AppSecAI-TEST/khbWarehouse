package com.yeepay.g3.app.lmweChat.utils;


/**
 * 页面显示相关处理工具
 * 
 * @author dongdong.tang
 * 
 */
public class PageShowUtils {
	
	/**
	 * 返回用户风险等级字符串
	 * @param riskLevel
	 * @return
	 */
	public static String getRiskString(String riskLevel) {
		if (ConstantUtils.RISK_LEVEL_SAFE.equals(riskLevel)) {
			return "安全型";
		}
		if (ConstantUtils.RISK_LEVEL_KEEP.equals(riskLevel)) {
			return "保守型";
		}
		if (ConstantUtils.RISK_LEVEL_STEADY.equals(riskLevel)) {
			return "稳健型";
		}
		if (ConstantUtils.RISK_LEVEL_ACTIVE.equals(riskLevel)) {
			return "积极型";
		}
		if (ConstantUtils.RISK_LEVEL_FORWARD.equals(riskLevel)) {
			return "进取型";
		}
		return "";
	}
	
}