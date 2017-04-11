package com.yeepay.g3.app.lmweChat.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	  
	    /**
	     * 
	     * @author hongbin.kang
	     * @date 2016年8月26日 下午3:32:37
	     * @param request
	     * @return
	     */
	    public static String getRequsetParams(HttpServletRequest request){
			Map<String, String[]> params = request.getParameterMap();  
	        String queryString = "";     
	        if(null != params && params.size() != 0) {
	        	//拼装参数
	        	for (String key : params.keySet()) {  
	        		String[] values = params.get(key);  
	        		for (int i = 0; i < values.length; i++) {  
	        			String value = values[i];  
	        			queryString += key + "=" + value + "&";  
	        		}  
	        	}
	        	// 去掉最后一个空格  
	        	queryString = queryString.substring(0, queryString.length() - 1);
	        }
	        return queryString;
		}
	    
	    /**
	     * 执行命令
	     * @param in -
	     * @return 
	     */

	    public static String callCmd(String[] cmd) { 
	        String result = ""; 
	        String line = ""; 
	        try { 
	          Process proc = Runtime.getRuntime().exec(cmd); 
	          InputStreamReader is = new InputStreamReader(proc.getInputStream()); 
	          BufferedReader br = new BufferedReader (is); 
	          while ((line = br.readLine ()) != null) { 
	          result += line; 
	          } 
	        } 
	        catch(Exception e) { 
	          e.printStackTrace(); 
	        } 
	        return result; 
	      }
	       
	      /** 
	      * 
	      * @param cmd 第一个命令 
	      * @param another 第二个命令 
	      * @return  第二个命令的执行结果 
	      */
	      public static String callCmd(String[] cmd,String[] another) { 
	        String result = ""; 
	        String line = ""; 
	        try { 
	          Runtime rt = Runtime.getRuntime(); 
	          Process proc = rt.exec(cmd); 
	          proc.waitFor(); //已经执行完第一个命令，准备执行第二个命令 
	          proc = rt.exec(another); 
	          InputStreamReader is = new InputStreamReader(proc.getInputStream()); 
	          BufferedReader br = new BufferedReader (is); 
	          while ((line = br.readLine ()) != null) { 
	            result += line; 
	          } 
	        } 
	        catch(Exception e) { 
	          e.printStackTrace(); 
	        } 
	        return result; 
	      }
 
	    /** 
	     * 
	     * @param ip 目标ip,一般在局域网内 
	     * @param sourceString 命令处理的结果字符串 
	     * @param macSeparator mac分隔符号 
	     * @return mac地址，用上面的分隔符号表示 
	     */
	     public static String filterMacAddress(final String ip, final String sourceString,final String macSeparator) { 
	       String result = ""; 
	       String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})"; 
	       Pattern pattern = Pattern.compile(regExp); 
	       Matcher matcher = pattern.matcher(sourceString); 
	       while(matcher.find()){ 
	         result = matcher.group(1); 
	         if(sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) { 
	           break; //如果有多个IP,只匹配本IP对应的Mac. 
	         } 
	       }
	     
	       return result; 
	     }

	     
	    /** 
	     * 
	     * @param ip 目标ip 
	     * @return  Mac Address 
	     * 
	     */
	     public static String getMacInWindows(final String ip){ 
	       String result = ""; 
	       String[] cmd = { 
	           "cmd", 
	           "/c", 
	           "ping " + ip 
	           }; 
	       String[] another = { 
	           "cmd", 
	           "/c", 
	           "arp -a"
	           }; 
	     
	       String cmdResult = callCmd(cmd,another); 
	       result = filterMacAddress(ip,cmdResult,"-"); 
	     
	       return result; 
	     } 

	     
	     /** 
	      * @param ip 目标ip 
	      * @return  Mac Address 
	      * 
	      */
	      public static String getMacInLinux(final String ip){ 
	        String result = ""; 
	        String[] cmd = { 
	            "/bin/sh", 
	            "-c", 
	            "ping " + ip + " -c 2 && arp -a"
	            }; 
	        String cmdResult = callCmd(cmd); 
	        result = filterMacAddress(ip,cmdResult,":"); 
	      
	        return result; 
	      } 

	      
	    /**
	     * 获取MAC地址 
	     * @return 返回MAC地址
	     */
	     public static String getMacAddress(String ip){
	       String macAddress = "";
	       macAddress = getMacInWindows(ip).trim();
	       if(macAddress==null||"".equals(macAddress)){
	         macAddress = getMacInLinux(ip).trim();
	       }
	       return macAddress;
	     }

	   //做个测试,baidu
	     public static void main(String[] args) {      
	       System.out.println(getMacAddress("106.121.6.133"));
	     }

}
