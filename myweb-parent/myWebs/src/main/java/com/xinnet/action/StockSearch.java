package com.xinnet.action;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinnet.annotation.NotLogin;

@Controller
@RequestMapping("stock")
public class StockSearch extends BaseAction {
	
	private static String httpStock = "http://hq.sinajs.cn/list=";
	
	@RequestMapping("search")
	@NotLogin
	public String toSearch(String code,ModelMap model) {
		if(StringUtils.isNotEmpty(code)) {
			String szCode = httpStock + "sz" + code;
			String stockResult = readHtml(szCode);
			String price = null;
			if(stockResult.indexOf(",") > 0) {
				price = this.getPrice(stockResult);
			} else {
				szCode = httpStock + "sh" + code;
				stockResult = readHtml(szCode);
				price = this.getPrice(stockResult);
			}
			return ajax(price);
		}
		return null;
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
