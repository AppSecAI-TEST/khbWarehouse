package com.xinnet.core.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * 对cookie进行，增，删，改，查询等操作
 * @author hongbin.kang
 * @date 2016年9月2日下午5:09:40
 */
public class CookieUtils {
	
	private static final Logger logger = Logger.getLogger(CookieUtils.class);

	/**
     * 添加cookie
     * @param response
     * @param name
     * @param value
     * @param time
     */
    public static void addCookie(HttpServletResponse response,String name,String value,Integer time){
    	if(time == null) {
    		time = -1;
    	}
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(time);// 设置生命周期
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    
    /**
     * 修改cookie
     * @param request
     * @param response
     * @param name
     * @param value
     * 注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
     */
    public static void editCookie(HttpServletRequest request,HttpServletResponse response,String name,String value){
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            logger.info("没有cookie==============");
        } else {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    logger.info("原值为:"+cookie.getValue());
                    cookie.setValue(value);
                    cookie.setPath("/");
                    cookie.setMaxAge(-1);
                    logger.info("被修改的cookie名字为:"+cookie.getName()+",新值为:"+cookie.getValue());
                    response.addCookie(cookie);
                    break;
                }
            }
        }
         
    }
    
    /**
     * 删除cookie
     * @param request
     * @param response
     * @param name
     */
    public static void delCookie(HttpServletRequest request,HttpServletResponse response,String name){
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
        	logger.info("没有cookie==============");
        } else {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    logger.info("被删除的cookie名字为:"+cookie.getName());
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
    
    /***
     * 根据名字获取cookie
     * @param request
     * @param name
     * @return
     */
    public static  Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }   
    }
      
    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
