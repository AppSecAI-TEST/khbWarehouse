package com.yeepay.g3.core.activity.utils;

import java.util.HashMap;
import java.util.Map;

import com.yeepay.g3.utils.common.json.JSONException;
import com.yeepay.g3.utils.common.json.JSONObject;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

public class JSONObjectUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JSONObjectUtils.class);

    /**
     * JSON串转Map
     * @param in -
     * @return 读取到的固定长度数据
     */
    public static Map<?,?> jsonToMap(Object str,Class keyType, Class objType){
    	if(str != null) {
    		Map<?,?> map = JSONUtils.jsonToMap(str.toString(), String.class, String.class);
    		return map;
    	}
    	return null;
    }
    
    public static void main(String args[]) throws JSONException{
    	String str = "{"+"userAgent"+":"+"sdfafdfsa"+","+"imei"+":"+"werwrew"+","+"macAddress"+":"+"sdafadfas"+","+"ip"+":"+"172.19.60.87"+"}";
    	JSONObject obj = new JSONObject(str);
    	Map<String,String> map = JSONUtils.jsonToMap(str, String.class, String.class);
    	System.out.println(map.get("ip"));
    	System.out.println(map.get("clientIp"));
    	
    }
}
