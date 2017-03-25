package com.yeepay.g3.core.activity.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yeepay.g3.utils.common.exception.YeepayRuntimeException;
import com.yeepay.g3.utils.common.httpclient.SimpleHttpParam;
import com.yeepay.g3.utils.common.httpclient.SimpleHttpResult;
import com.yeepay.g3.utils.common.httpclient.SimpleHttpUtils;
/**
 * <p>Title:  Http请求工具类</p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:懒猫金服</p>
 * @author ying.liu
 * @version 2016-3-22
 */
public class HttpRequestUtils {

	/**
	 * 
	 * 发送Http请求
	 *
	 */
	public static String sendHttpRequest(String requestUrl,String requestMethod,Map<String,String> paramMap){
		try{
			SimpleHttpParam param = new SimpleHttpParam(requestUrl);
			Map<String,String> headers = new HashMap<String,String>();
			headers.put("Connection", "close");
			param.setHeaders(headers);
			param.setParameters(paramMap);
			param.setMethod(requestMethod);
			param.setSslVerify(false);
			SimpleHttpResult result = SimpleHttpUtils.httpRequest(param);
			String queryResult = result.getContent();
			return queryResult;
			
		}catch(Exception e){
			throw new YeepayRuntimeException("Http请求时出现异常",e);
		}
	}
	/**
	 * 构建url 数组参数变为逗号分隔
	 * @author cdt
	 * @date 2016-3-24
	 * @time 下午3:26:00
	 */
	  public static String buildReturnUrl(HttpServletRequest request) {
	        String returnUrl;
	        Map<Object, Object> paramMap = request.getParameterMap();
	        if (paramMap != null && paramMap.size() != 0) {
	            StringBuilder requestParams = new StringBuilder("");
	            for (Map.Entry<Object, Object> entry : paramMap.entrySet()) {
	                StringBuilder value = new StringBuilder("");
	                for (String valueItem : (String[]) entry.getValue()) {
	                    value.append(",").append(valueItem);
	                }
	                requestParams.append("&").append(entry.getKey()).append("=")
	                        .append(value.substring(1));
	            }
	            returnUrl = request.getRequestURL().append("?").append(requestParams.substring(1)).toString();
	        } else {
	            returnUrl = request.getRequestURL().toString();
	        }
	        try {
	            return URLEncoder.encode(returnUrl, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            return "";
	        }
	    }
	  
	  public static String buildReturnUrlWithoutParams(HttpServletRequest request) {
	        String returnUrl = request.getRequestURL().toString();
	        try {
	            return URLEncoder.encode(returnUrl, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            return "";
	        }
	    }

	    public static String buildReturnUrl(HttpServletRequest request, String servletPath) {
	        String returnUrl = request.getScheme() + "://"
	                + request.getServerName() + ":" + request.getServerPort()
	                + request.getContextPath() + "/" + servletPath;
	        try {
	            return URLEncoder.encode(returnUrl, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            return "";
	        }
	    }
	    /** 
	     * 发送HttpPost请求 
	     *  
	     * @param strURL 
	     *            服务地址 
	     * @param params 
	     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
	     * @return 成功:返回json字符串<br/> 
	     */  
	    public static String post(String strURL, String params) {  
	        try {  
	            URL url = new URL(strURL);// 创建连接  
	            HttpURLConnection connection = (HttpURLConnection) url  
	                    .openConnection();  
	            connection.setDoOutput(true);  
	            connection.setDoInput(true);  
	            connection.setUseCaches(false);  
	            connection.setInstanceFollowRedirects(true);  
	            connection.setRequestMethod("POST"); // 设置请求方式  
	            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
	            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
	            connection.connect();  
	            OutputStreamWriter out = new OutputStreamWriter(  
	                    connection.getOutputStream(), "UTF-8"); // utf-8编码  
	            out.append(params);  
	            out.flush();  
	            out.close();  
	            // 读取响应  
	            int length = (int) connection.getContentLength();// 获取长度  
	            InputStream is = connection.getInputStream();  
	            if (length != -1) {  
	                byte[] data = new byte[length];  
	                byte[] temp = new byte[512];  
	                int readLen = 0;  
	                int destPos = 0;  
	                while ((readLen = is.read(temp)) > 0) {  
	                    System.arraycopy(temp, 0, data, destPos, readLen);  
	                    destPos += readLen;  
	                }  
	                String result = new String(data, "UTF-8"); // utf-8编码  
	                System.out.println(result);  
	                return result;  
	            }  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return "error"; // 自定义错误信息  
	    }  
	  

}
