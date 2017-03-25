package com.yeepay.g3.core.activity.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
public class GetParamUtils {

	public static final String COMMON_MESSAGE_SPLIT = "_";
	
	
	/**
	 * 读取兑换码活动的事件编码
	 * @param in -
	 * @return 
	 */
	public static Map<?,?> readActionCode(){
		ConfigParam<?> config = ConfigurationUtils.getConfigParam("config_type_text_resources", "lm_act_redeem_code_config");
		Map<?,?> map = config.getValue() == null ? new LinkedHashMap<String,String>() : (Map<?,?>)config.getValue(); 
		return map;
	}
	
}
