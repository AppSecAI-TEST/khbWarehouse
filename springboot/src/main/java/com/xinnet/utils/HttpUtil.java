package com.xinnet.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Http的工具类
 * @author hongbin.kang
 * @date 2016年9月2日下午4:45:15
 */
public class HttpUtil {
	
	private static final Logger logger = Logger.getLogger(HttpUtil.class);
	
	private static final String URL_PARAM_CONNECT_FLAG = "&";
	
	/** 
     * 定义编码格式 UTF-8 
     */  
    public static final String UTF8 = "UTF-8";  
      
    /** 
     * 定义编码格式 GBK 
     */  
    public static final String GBK = "GBK";  
      
    private static final String EMPTY = "";  
  
    private static int connectionTimeOut = 25000;  
  
    private static int socketTimeOut = 25000;  
  
    private static int maxConnectionPerHost = 20;  
  
    private static int maxTotalConnections = 20;  
  
	
	/**
	 * 拼装request里面的参数
	 * @author hongbin.kang
	 * @date 2016年8月22日 上午10:20:45
	 * @param request
	 * @return
	 */
	public static String getRequsetParams(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap(); 
		String queryString = "";
		StringBuffer data = new StringBuffer();
        logger.info("截获的参数为："+params);
        if(null != params && params.size() != 0) {
        	//拼装参数
        	for (String key : params.keySet()) {  
        		String[] values = params.get(key);  
        		for (int i = 0; i < values.length; i++) {  
        			String value = values[i];
        			data.append(key).append("=").append(value).append(URL_PARAM_CONNECT_FLAG);
        		}  
        	}
        	// 去掉最后一个字符
        	queryString = data.toString().substring(0, data.length() - 1);
        }
        return queryString;
	}
	
      
       
  
    /** 
     * 据Map生成URL字符串 
     * @param map 
     *          Map 
     * @return 
     *          URL 
     */  
    private static String getUrlParams(Map<String, String> map) {  
          
        if (null == map || map.keySet().size() == 0) {  
            return (EMPTY);  
        }  
        StringBuffer urlParams = new StringBuffer();  
        Set<String> keys = map.keySet();  
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {  
            String key = it.next();  
            if (map.containsKey(key)) {  
                String val = map.get(key);  
                String str = val != null ? val : EMPTY;  
                try {  
                    str = URLEncoder.encode(str, UTF8);  
                } catch (UnsupportedEncodingException e) {  
                    e.printStackTrace();  
                }  
                urlParams.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);  
            }  
        }  
        String strURL = EMPTY;  
        strURL = urlParams.toString();  
        if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {  
            strURL = strURL.substring(0, strURL.length() - 1);  
        }  
          
        return (strURL);  
    }  
}
