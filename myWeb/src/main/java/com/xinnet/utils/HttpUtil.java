package com.xinnet.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
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
  
    private static MultiThreadedHttpConnectionManager connectionManager = null;  
  
    private static int connectionTimeOut = 25000;  
  
    private static int socketTimeOut = 25000;  
  
    private static int maxConnectionPerHost = 20;  
  
    private static int maxTotalConnections = 20;  
  
    private static HttpClient client;  
  
    static{  
        connectionManager = new MultiThreadedHttpConnectionManager();  
        connectionManager.getParams().setConnectionTimeout(connectionTimeOut);  
        connectionManager.getParams().setSoTimeout(socketTimeOut);  
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);  
        connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);  
        client = new HttpClient(connectionManager);  
    }  
	
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
     * POST方式提交数据 
     * @param url 
     *          待请求的URL 
     * @param params 
     *          要提交的数据 
     * @return 
     *          响应结果 
     * @throws IOException 
     *          IO异常 
     */  
    public static String URLPost(String url, Map<String, String> params){  
  
        String response = EMPTY;          
        PostMethod postMethod = null;  
        try {  
            postMethod = new PostMethod(url);  
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset="+UTF8);  
            //将表单的值放入postMethod中  
            Set<String> keySet = params.keySet();  
            for(String key : keySet){  
                String value = params.get(key);  
                postMethod.addParameter(key, value);  
            }             
            //执行postMethod  
            int statusCode = client.executeMethod(postMethod);  
            if(statusCode == HttpStatus.SC_OK) {  
                response = postMethod.getResponseBodyAsString();  
            }else{  
            	logger.error("响应状态码 = " + postMethod.getStatusCode());  
            }  
        }catch(HttpException e){  
        	logger.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);  
            e.printStackTrace();  
        }catch(IOException e){  
        	logger.error("发生网络异常", e);  
            e.printStackTrace();  
        }finally{  
            if(postMethod != null){  
                postMethod.releaseConnection();  
                postMethod = null;  
            }  
        }  
        return response;  
    }  
      
    /** 
     * GET方式提交数据 
     * @param url 
     *          待请求的URL 
     * @param params 
     *          要提交的数据 
     * @return 
     *          响应结果 
     * @throws IOException 
     *          IO异常 
     */  
    public static String URLGet(String url, Map<String, String> params){  
  
        String response = EMPTY;  
        GetMethod getMethod = null;       
        StringBuffer strtTotalURL = new StringBuffer(EMPTY);  
          
        if(strtTotalURL.indexOf("?") == -1) {  
          strtTotalURL.append(url).append("?").append(getUrlParams(params));  
        } else {  
            strtTotalURL.append(url).append("&").append(getUrlParams(params));  
        }  
        logger.debug("GET请求URL = \n" + strtTotalURL.toString());  
          
        try {  
            getMethod = new GetMethod(strtTotalURL.toString());  
            getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + UTF8);  
            //执行getMethod  
            int statusCode = client.executeMethod(getMethod);  
            if(statusCode == HttpStatus.SC_OK) {  
                response = getMethod.getResponseBodyAsString();  
            }else{  
            	logger.debug("响应状态码 = " + getMethod.getStatusCode());  
            }  
        }catch(HttpException e){  
        	logger.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);  
            e.printStackTrace();  
        }catch(IOException e){  
        	logger.error("发生网络异常", e);  
            e.printStackTrace();  
        }finally{  
            if(getMethod != null){  
                getMethod.releaseConnection();  
                getMethod = null;  
            }  
        }  
          
        return response;  
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
