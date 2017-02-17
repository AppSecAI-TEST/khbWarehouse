package com.xinnet.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {
	
	/**
	 * map的属性转换为驼峰式字段
	 * @author hongbin.kang
	 * @date 2016年10月28日 上午11:31:59
	 * @param map
	 * @return
	 */
	public static Map<String,Object> toCamelCaseMap(Map<String,?> map) {  
		Map<String, Object> newMap = new HashMap<String, Object>();
		if(null == map) {
			return null;
		}
        for (String key : map.keySet()) { 
        	newMap.put(toCamelCaseString(key), map.get(key));
        }  
        return newMap;
    }
	
	/**
	 * 将list里面的map的属性字段，转换为驼峰式字段
	 * @author hongbin.kang
	 * @date 2016年10月28日 上午11:38:04
	 * @param list
	 * @return
	 */
	public static List<Map<String,Object>> toCamelCaseList(List<Map<String,?>> list) {  
		List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
        if(null == list || list.size() == 0) {
        	return null;
        }
		for (Map<String,?> map: list) {
			Map<String, Object> newMap = new HashMap<String, Object>();
			newMap = toCamelCaseMap(map);
			newList.add(newMap);
        }  
        return newList;
    }
	
	/**
	 * 将属性字符串转成属性驼峰字段
	 * @author hongbin.kang
	 * @date 2016年10月28日 上午11:15:44
	 * @param inputString
	 * @return
	 */
    public static String toCamelCaseString(String inputString) {  
        return toCamelCaseString(inputString, false);  
    }  
  
    /**
     * 将属性字符串转成属性驼峰字段 
     * @author hongbin.kang
     * @date 2016年10月28日 上午11:16:57
     * @param inputString
     * @param firstCharacterUppercase 首字母是否大写
     * @return
     */
    public static String toCamelCaseString(String inputString, boolean firstCharacterUppercase) {  
        if (inputString == null)  
            return null;  
        StringBuilder sb = new StringBuilder();  
        boolean nextUpperCase = false;  
        for (int i = 0; i < inputString.length(); i++) {  
            char c = inputString.charAt(i);  
  
            switch (c) {  
            case '_':  
            case ' ':  
                if (sb.length() > 0) {  
                    nextUpperCase = true;  
                }  
                break;  
  
            default:  
                if (nextUpperCase) {  
                    sb.append(Character.toUpperCase(c));  
                    nextUpperCase = false;  
                } else {  
                    sb.append(Character.toLowerCase(c));  
                }  
                break;  
            }  
        }  
  
        if (firstCharacterUppercase) {  
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));  
        }  
  
        return sb.toString();  
    }
    
    public static void main(String[] args) {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("a_ture", "asb");
    	map.put("ab_operate", "djfkk");
    	map.put("china_chinase", "uew");
    	map.put("zp", "khb");
    	Map<String, Object> a = MapUtil.toCamelCaseMap(map);
    	System.out.println(a);
	}

}
