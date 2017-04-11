package com.yeepay.g3.app.lmweChat.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.yeepay.g3.app.dto.PersonalDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.BindCardStatusEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.utils.common.json.JSONException;
import com.yeepay.g3.utils.common.json.JSONObject;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

public class JSONObjectUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JSONObjectUtils.class);

	/**
	 * 会员操作结果转换为json字符串
	 * @param userOpeDto
	 * @return
	 */
	public static String userOpeDtoToJsonStr(UserOpeDTO userOpeDto) {
		JSONObject obj = new JSONObject();
		logger.info("userOpeDto={}", userOpeDto);
		try {
			obj.put("resultCode", userOpeDto.getResultCode());
			obj.put("errorCode", userOpeDto.getErrorCode());
			obj.put("errorMsg", userOpeDto.getErrorMsg());
			obj.put("userSessionKey", userOpeDto.getUserSessionKey());
			obj.put("memberNo", userOpeDto.getMemberNo());
			obj.put("memberTel", userOpeDto.getMemberTel());
			obj.put("realName", userOpeDto.getRealName());
			obj.put("identityNo", userOpeDto.getIdentityNo());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return obj.toString();
	}
	
	/**
	 * 实体转json字符串
	 * @author ping.zhu
	 * @CreateTime 2016年8月11日 下午13:55:20
	 * @version 1.0
	 */
	public static String dtoToJSON(Object source){
    	JSONObject obj = new JSONObject();
    	Class c=source.getClass();
    	Field[] fields=c.getDeclaredFields();
        if(c.getSuperclass()!=null){
            Class clsSup = c.getSuperclass();
            fields = (Field[]) ArrayUtils.addAll(fields, clsSup.getDeclaredFields());
        }
    	Method method=null;
    	StringBuffer sb=null;
    	for(Field field : fields){
    		sb=new StringBuffer();
    		sb.append("get");
    		sb.append(field.getName().substring(0, 1).toUpperCase());
    		sb.append(field.getName().substring(1, field.getName().length()));
    		try {
				method=c.getMethod(sb.toString());
	        	System.out.println(method);
				obj.put(field.getName(), method.invoke(source, new Object[] {}));
			} catch (Exception e) {
	            throw new RuntimeException(e);  
			}
    	}
		return obj.toString();
	}
	/** 
	 * @author hongbin.kang
	 * @CreateTime 2016年8月1日  18:43:41
	 * @version 1.0
     * 实体的值转成JSON对象的值 
     * @param source 
     * @param dest 
	 * @return 
     */  
    public static String entityToJSON(Object source) {  
    	JSONObject obj = new JSONObject();
        Class kclass = source.getClass();  
        Field[] fields = kclass.getDeclaredFields();
        if(kclass.getSuperclass()!=null){
            Class clsSup = kclass.getSuperclass();
            fields = (Field[]) ArrayUtils.addAll(fields, clsSup.getDeclaredFields());
        }
        try {  
            for (Field field : fields) { 
            	obj.put(field.getName(), getFieldValue(source, field  
            			.getName()));  
            	System.out.println(getFieldValue(source, field  
            			.getName()));
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } 
        return obj.toString();
    }  
  
    /** 
     * 获取字段的值 
     * @author hongbin.kang
     * @param data 
     * @param fieldName 
     * @return 
     */  
    public static Object getFieldValue(Object data, String fieldName) {  
          
        StringBuilder sb = new StringBuilder();  
        Class kclass = data.getClass();  
        //将字段首字母大写  
        String firstWord = fieldName.substring(0, 1).toUpperCase();  
        sb.append(firstWord);  
        sb.append(fieldName.substring(1, fieldName.length()));  
        final String methodName = "get" + sb.toString();  
        Method[] methods = kclass.getMethods(); 
//        if(kclass.getSuperclass()!=null){
//            Class clsSup = kclass.getSuperclass();
//            methods = (Method[]) ArrayUtils.addAll(methods, clsSup.getDeclaredMethods());
//        }
        try {  
            for (Method method : methods) {  
                // 调用对应的方法  
                if (methodName.equals(method.getName())) {  
                    return method.invoke(data, new Object[] {});  
                }  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
        return null;  
    }  
	
    /**
     * 将用户地址信息转为json串
     * @param ip ip地址
     * @param macAddress MAC地址
     * @param userAgent 用户代理
     * @param imei 手机唯一标识
     * @return JSON
     */
    public static Object userAddrToJSONStr(String ip,String macAddress,String userAgent,String imei){
    	JSONObject obj = new JSONObject();
		try{
			obj.put("ip", ip);
			obj.put("macAddress", macAddress);
			obj.put("userAgent", userAgent);
			obj.put("imei", imei);
		}catch(Exception e){
			e.printStackTrace();
		}
    	return obj;
    }
    /**
     * JSON串转Map
     * @param in -
     * @return 读取到的固定长度数据
     */
    public static Map<?,?> jsonToMap(String str,Class keyType, Class objType){
    	Map<?,?> map = JSONUtils.jsonToMap(str, String.class, String.class);
    	return map;
    }
    
    public static void main(String args[]) throws JSONException{
		/*PersonalDTO personalDto = new PersonalDTO();
		personalDto.setBindMobileNo("12345667");
		personalDto.setCreateDate(new Date().toString());
		personalDto.setCredentialsNo("6212260404003421322");
		personalDto.setErrorCode("0000");
		personalDto.setErrorMsg("SUCCESS");
		personalDto.setIdentityNo("320322199309181111");
		personalDto.setLoginName("18611546919");
		personalDto.setMemberNo("888818611546919");
		personalDto.setMemberTel("18611546919");
		personalDto.setRealName("朱平");
		personalDto.setResultCode(ResultCodeEnum.SUCCESS);
		personalDto.setStatus(BindCardStatusEnum.BIND);
		personalDto.setTradePwd("**********");
		personalDto.setUserSessionKey("ccf6ba24534577df6da071ce365f374b");
		System.out.println(dtoToJSON(personalDto));*/
//    	String str = "{"ip":"172.19.60.87"}";
    	String str = "{"+"userAgent"+":"+"sdfafdfsa"+","+"imei"+":"+"werwrew"+","+"macAddress"+":"+"sdafadfas"+","+"ip"+":"+"172.19.60.87"+"}";
//    	Object obj1 = JSONObjectUtils.userAddrToJSONStr("172.19.60.87","sdafadfas", "sdfafdfsa", "werwrew");
//    	System.out.println(obj1.toString());
//    	System.out.println(str);
//    	net.sf.json.JSONObject obj = net.sf.json.JSONObject.
    	JSONObject obj = new JSONObject(str);
    	Map<String,String> map = JSONUtils.jsonToMap(str, String.class, String.class);
//    	System.out.println(net.sf.json.JSONObject.toBean(obj,UserOpeDTO.class));
    	System.out.println(map.get("ip"));
    	
    }
}
