package com.yeepay.g3.boss.activity.utils;

import com.yeepay.g3.utils.common.json.JSONUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author by menghao.chen
 */
@Component
public class JsonHelper {
	public String getAttribute(String target, String key) {
		if(target==null||!target.trim().startsWith("{")){
			return null;
		}
		Map<String, String> targetMap = JSONUtils.jsonToMap(target, String.class, String.class);
		return targetMap.get(key);
	}
}
