package com.xinnet.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
 






import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
 
/**
 * 抓取网页工具类
 * @author g-gaojp
 * @date 2016-7-10
 */
public class HtmlCrawler {
 
    /**
     * 获取网页数据
     * @param urlStr  访问地址
     * @param params  参数
     * @param charset 字符编码
     * @return
     * @throws Exception
     */
    public static String httpGet(String urlStr, Map<String, String> params,String charset) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (null != params && params.size() > 0) {
            sb.append("?");
            Entry<String, String> en;
            for (Iterator<Entry<String, String>> ir = params.entrySet().iterator(); ir.hasNext();) {
                en = ir.next();
                sb.append(en.getKey() + "=" + URLEncoder.encode(en.getValue(),"utf-8") + (ir.hasNext() ? "&" : ""));
            }
        }
        URL url = new URL(urlStr + sb);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200){
            throw new Exception("请求异常状态值:" + conn.getResponseCode());
        }
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        Reader reader = new InputStreamReader(bis,charset);
        char[] buffer = new char[2048];
        int len = 0;
        CharArrayWriter caw = new CharArrayWriter();
        while ((len = reader.read(buffer)) > -1)
            caw.write(buffer, 0, len);
        reader.close();
        bis.close();
        conn.disconnect();
        return caw.toString();
    }
     
    /**
     * 获取网页数据
     * @param urlStr  访问地址
     * @param params  参数
     * @return
     * @throws Exception
     */
    public static String httpGet(String urlStr, Map<String, String> params) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (null != params && params.size() > 0) {
            sb.append("?");
            Entry<String, String> en;
            for (Iterator<Entry<String, String>> ir = params.entrySet().iterator(); ir.hasNext();) {
                en = ir.next();
                sb.append(en.getKey() + "=" + URLEncoder.encode(en.getValue(),"utf-8") + (ir.hasNext() ? "&" : ""));
            }
        }
        URL url = new URL(urlStr + sb);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200)
            throw new Exception("请求异常状态值:" + conn.getResponseCode());
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        Reader reader = new InputStreamReader(bis,"gbk");
        char[] buffer = new char[2048];
        int len = 0;
        CharArrayWriter caw = new CharArrayWriter();
        while ((len = reader.read(buffer)) > -1)
            caw.write(buffer, 0, len);
        reader.close();
        bis.close();
        conn.disconnect();
        //System.out.println(caw);
        return caw.toString();
    }
     
 
    /**
     * 输入流转为document
     * @author hongbin.kang
     * @date 2017年4月12日 下午6:40:50
     * @param in
     * @return
     */
    public static Document inputStreamTransToDocument(InputStream in,String charsetName){
    	Document doc = null;
		try {
			if(null == in) {
				return null;
			}
			doc = Jsoup.parse(in, charsetName, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return doc;
    }
    
    /**
     * 输入流转为document
     * @author hongbin.kang
     * @date 2017年4月12日 下午6:40:50
     * @param in
     * @return
     */
    public static Document inputStreamTransToDocument(byte[] byteArry,String charsetName){
    	Document doc = null;
    	InputStream in = new ByteArrayInputStream(byteArry);
    	doc = inputStreamTransToDocument(in,charsetName);
        return doc;
    }
    
    /**
     * 从获得的网页的document中获取指定条件的内容
     * @param document
     * @param condition 条件
     * @return
     */
    public static String catchInfomationFromDocument(Document document , String condition , int position){
         
        if(document != null){
            Iterator<Element> iterator = document.select(condition).iterator();
            Element elemtId = document.getElementById(condition);//
            System.out.println(elemtId.text());
            if(iterator.hasNext()){
                String str = iterator.next().text();
                return str.substring(position).trim();
            }
        }
        
        return null;
    }
     
    /**
     * 判断从获得的网页的document中<br/>
     * 获取指定条件的内容是否存在
     * @param document
     * @param condition 条件
     * @return
     */
    public static boolean isExistInfomation(Document document , String condition){
         
        if(document != null){
            Iterator<Element> iterator = document.select(condition).iterator();
            if(iterator.hasNext()){
                return true;
            }
        }
        return false;
    }
 
    //读取页面
  	public static byte[] readHtml(String linkUrl) {
  		byte[] responseBody = null;  
  		if(null != linkUrl) {
  			//连接http服务器端  
  			HttpClient httpClient=new HttpClient();  
  			GetMethod getMethod=new GetMethod(linkUrl);
  			//防止报403错误
  			getMethod.addRequestHeader("User-Agent", "Mozilla/31.0 (compatible; MSIE 10.0; Windows NT; DigExt)"); 
  			try{  
  				int statusCode = httpClient.executeMethod(getMethod);  
  				if(statusCode != HttpStatus.SC_OK){  
  					System.err.println("Method failed: "+getMethod.getStatusLine());  
  				}  
  				//byte[] responseBody=getMethod.getResponseBody();  
  				//System.out.println(new String(responseBody));  
  				
  				//当网页内容数据量大时推荐使用  
  				InputStream in = getMethod.getResponseBodyAsStream(); 
  				if (in != null) {  
  					byte[] tmp = new byte[4096];  
  					int bytesRead = 0;  
  					ByteArrayOutputStream buffer = new ByteArrayOutputStream(1024);  
  					while ((bytesRead = in.read(tmp)) != -1) {  
  						buffer.write(tmp, 0, bytesRead);  
  					}  
  					responseBody = buffer.toByteArray();  
  					System.out.println(new String(responseBody,"gb2312"));  
  				}  
  			}catch(HttpException e){  
  				System.out.println("Please check your provided http address!");  
  				e.printStackTrace();  
  			}catch(IOException e){  
  				e.printStackTrace();  
  			}catch(Exception ex){  
  				System.out.println("Error:"+ex.toString());  
  			}finally{             
  				getMethod.releaseConnection();  
  			} 
  		}
  		return responseBody;
  	}
  	
  	public static void main(String[] args) throws Exception {
  		byte[] by = readHtml("http://quote.eastmoney.com/sz002461.html?from=BaiduAladdin");
  		FileOutputStream fops = FileUtils.openOutputStream(new File("E:/crawler/html/" + "珠江啤酒" + ".html"),true);
		fops.write(by);  
		fops.flush();  
		fops.close();
  		Document document = inputStreamTransToDocument(by,"gb2312");
  		String a = catchInfomationFromDocument(document,"price9",0);
  		System.out.println(a);
	}
  	
}
