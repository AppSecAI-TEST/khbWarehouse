package com.xinnet.task.service.impl;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xinnet.task.service.IQueryStockService;

@Service
public class QueryStockServiceImpl implements IQueryStockService {

	private static final Logger logger = LoggerFactory.getLogger(QueryStockServiceImpl.class);

	private static String httpStock = "http://hq.sinajs.cn/list=";
	
	@Override
	public void queryStock() {
		String szCode = httpStock + "sz" + "002461";
		String stockResult = readHtml(szCode);
		String price = null;
		if(stockResult.indexOf(",") > 0) {
			price = this.getPrice(stockResult);
		} else {
			szCode = httpStock + "sh" + "002461";
			stockResult = readHtml(szCode);
			price = this.getPrice(stockResult);
		}
		System.out.println(price);
	}
	
	private String getPrice(String stockResult) {
		String price = stockResult.split(",")[3];
		return price;
	}
	
	//读取页面
	public static String readHtml(String linkUrl) {
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
				byte[] responseBody=getMethod.getResponseBody();  
				return new String(responseBody,"gbk");
			
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
		return null;
	}
	
}
