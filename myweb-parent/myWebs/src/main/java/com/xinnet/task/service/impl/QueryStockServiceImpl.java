package com.xinnet.task.service.impl;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.xinnet.entity.Stock;
import com.xinnet.task.service.IQueryStockService;

@Service
@PropertySource("classpath:stock.properties") 
public class QueryStockServiceImpl implements IQueryStockService {

	private static final Logger logger = LoggerFactory.getLogger(QueryStockServiceImpl.class);

	private static String httpStock = "http://hq.sinajs.cn/list=";
	
    @Value("${stock.name}")  
	private String name;  
    
    @Value("${stock.code}")  
	private String code;  
    
    @Value("${stock.share}")  
	private String share;  
    
    @Value("${stock.value}")  
	private String value;  

    @Override
	public void queryStock() {
    	
    	Stock stock = new Stock(name,code,share,value);
    	BigDecimal totalMoney = BigDecimal.ZERO;
    	
    	for(int i = 0;i<stock.getNames().size();i++) {
    		//股票名称
    		String name = stock.getNames().get(i);
    		//股票代码
    		String code = stock.getCode().get(i);
    		//股票份额
    		BigDecimal share = new BigDecimal(stock.getShare().get(i));
    		//股票股价
    		BigDecimal value = new BigDecimal(stock.getValue().get(i));
    		
    		//检测是否是深圳的股票
    		String szCode = httpStock + "sz" + code;
    		String stockResult = readHtml(szCode);
    		BigDecimal price = null;
    		//不是深圳尝试上海
    		if(stockResult.indexOf(",") > 0) {
    			price = this.getPrice(stockResult);
    		} else {
    			szCode = httpStock + "sh" + code;
    			stockResult = readHtml(szCode);
    			price = this.getPrice(stockResult);
    		}
    		System.out.println(name +"--" +price );
    		totalMoney = totalMoney.add(price.subtract(value).multiply(share));
    		
    	}
    	System.out.println("总盈亏 : "+totalMoney);
    	

	}
	
	private void queryTRStock() {
		String szCode = httpStock + "sz" + "002283";
		String stockResult = readHtml(szCode);
		BigDecimal price = null;
		if(stockResult.indexOf(",") > 0) {
			price = this.getPrice(stockResult);
		} else {
			szCode = httpStock + "sh" + "002283";
			stockResult = readHtml(szCode);
			price = this.getPrice(stockResult);
		}
		System.out.println("天润--" + price);
	}
	
	
	private BigDecimal getPrice(String stockResult) {
		String price = stockResult.split(",")[3];
		return new BigDecimal(price);
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
