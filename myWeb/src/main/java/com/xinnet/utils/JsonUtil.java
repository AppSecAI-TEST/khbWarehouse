package com.xinnet.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinnet.entity.User;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class JsonUtil {     
    
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    
	/**
	 * 实体和map转为JSONObject进行操作
	 * @author hongbin.kang
	 * @date 2016年10月13日 下午3:44:42
	 * @param obj
	 * @return
	 */
	public static JSONObject getJSONObject(Object obj) {
		if(null == obj) {
			return null;
		}
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(obj);  
		} catch (Exception e) {
			logger.error("obj转换为JSONObject类失败,obj={"+obj.toString()+"}",e);
			// TODO: handle exception
		}
        return jsonObject;     
    }
   
	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，形如：   
     * {"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}}  
	 * @author hongbin.kang
	 * @date 2016年10月13日 下午3:46:10
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
    public static Object getDTO(Object obj, Class clazz){     
        JSONObject jsonObject = null;     
        setDataFormat2JAVA();      
        jsonObject = getJSONObject(obj);
        if(null == jsonObject) {
        	return null;
        }
        return JSONObject.toBean(jsonObject, clazz);     
    }     
         
    /**   
     * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如：   
     * {"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...},   
     * beansList:[{}, {}, ...]}   
     * @param jsonString   
     * @param clazz   
     * @param map 集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" : Bean.class)   
     * @return   
     */    
    public static Object getDTO(Object obj, Class clazz, Map map){     
        JSONObject jsonObject = null;     
        setDataFormat2JAVA();      
        jsonObject = getJSONObject(obj);  
        if(null == jsonObject) {
        	return null;
        }
        return JSONObject.toBean(jsonObject, clazz, map);     
    }     
         
    /**   
     * 从一个JSON数组得到一个java对象数组，形如：   
     * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...]   
     * @param object   
     * @param clazz   
     * @return   
     */    
    public static Object[] getDTOArray(String jsonString, Class clazz){     
        setDataFormat2JAVA();     
        JSONArray array = JSONArray.fromObject(jsonString);     
        Object[] obj = new Object[array.size()];     
        for(int i = 0; i < array.size(); i++){     
            JSONObject jsonObject = array.getJSONObject(i);     
            obj[i] = JSONObject.toBean(jsonObject, clazz);     
        }     
        return obj;     
    }     
         
    /**   
     * 从一个JSON数组得到一个java对象数组，形如：   
     * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...]   
     * @param object   
     * @param clazz   
     * @param map   
     * @return   
     */    
    public static Object[] getDTOArray(String jsonString, Class clazz, Map map){     
        setDataFormat2JAVA();     
        JSONArray array = JSONArray.fromObject(jsonString);     
        Object[] obj = new Object[array.size()];     
        for(int i = 0; i < array.size(); i++){     
            JSONObject jsonObject = array.getJSONObject(i);     
            obj[i] = JSONObject.toBean(jsonObject, clazz, map);     
        }     
        return obj;     
    }     
         
    /**   
     * 从一个JSON数组得到一个java对象集合   
     * @param object   
     * @param clazz   
     * @return   
     */    
    public static List getDTOList(String jsonString, Class clazz){     
        setDataFormat2JAVA();     
        JSONArray array = JSONArray.fromObject(jsonString);     
        List list = new ArrayList();     
        for(Iterator iter = array.iterator(); iter.hasNext();){     
            JSONObject jsonObject = (JSONObject)iter.next();     
            list.add(JSONObject.toBean(jsonObject, clazz));     
        }     
        return list;     
    }     
         
    /**   
     * 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性   
     * @param object   
     * @param clazz   
     * @param map 集合属性的类型   
     * @return   
     */    
    public static List getDTOList(String jsonString, Class clazz, Map map){     
        setDataFormat2JAVA();     
        JSONArray array = JSONArray.fromObject(jsonString);     
        List list = new ArrayList();     
        for(Iterator iter = array.iterator(); iter.hasNext();){     
            JSONObject jsonObject = (JSONObject)iter.next();     
            list.add(JSONObject.toBean(jsonObject, clazz, map));     
        }     
        return list;     
    }     
         
    /**   
     * 从json HASH表达式中获取一个map，该map支持嵌套功能   
     * 形如：{"id" : "johncon", "name" : "小强"}   
     * 注意commons-collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap   
     * @param object   
     * @return   
     */    
    public static Map getMapFromJson(String jsonString) {     
        setDataFormat2JAVA();     
        JSONObject jsonObject = JSONObject.fromObject(jsonString);     
        Map map = new HashMap();     
        for(Iterator iter = jsonObject.keys(); iter.hasNext();){     
            String key = (String)iter.next();     
            map.put(key, jsonObject.get(key));     
        }     
        return map;     
    }
    
         
    /**   
     * 从json数组中得到相应java数组   
     * json形如：["123", "456"]   
     * @param jsonString   
     * @return   
     */    
    public static Object[] getObjectArrayFromJson(String jsonString) {     
        JSONArray jsonArray = JSONArray.fromObject(jsonString);     
        return jsonArray.toArray();     
    }     
    
    
    /**   
     * 把数据对象转换成json字符串   
     * DTO对象形如：{"id" : idValue, "name" : nameValue, ...}   
     * 数组对象形如：[{}, {}, {}, ...]   
     * map对象形如：{key1 : {"id" : idValue, "name" : nameValue, ...}, key2 : {}, ...}   
     * @param object   
     * @return   
     */    
    public static String getJSONString(Object object) throws Exception{     
        String jsonString = null;     
        //日期值处理器     
        JsonConfig jsonConfig = new JsonConfig();     
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());     
        if(object != null){     
            if(object instanceof Collection || object instanceof Object[]){     
                jsonString = JSONArray.fromObject(object, jsonConfig).toString();     
            }else{     
                jsonString = JSONObject.fromObject(object, jsonConfig).toString();     
            }     
        }     
        return jsonString == null ? "{}" : jsonString;     
    }     
         
    private static void setDataFormat2JAVA(){     
        //设定日期转换格式     
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));     
    }     
         
    public static void main(String[] arg) throws Exception{    
    	
        String s = "{id : '1',userName:'abcd',password:'123456ok'}";     
        System.out.println(" object : " + JsonUtil.getJSONString(s));   
        User u = new User();
        u.setId(11);
        u.setPassWord("1234567890");
        System.out.println(u);
        JSONObject j = getJSONObject(u);
        System.out.println(j);
        Map<String, String> m = (Map<String, String>)getMapFromJson(getJSONObject(u).toString());
        System.out.println(m);
        Map<String, String> h = new HashMap<String, String>();
        h.put("id", "123");
        h.put("a", "po");
        h.put("age", "25");
        h.put("userName", "遮天");
        User hu = (User) getDTO(getJSONObject(s).toString(), User.class);
        System.out.println(hu);
    }     
}
