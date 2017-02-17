package com.xinnet.utils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerUtils{ 
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlerUtils.class);
	
	
	static List<String> linkLists = new ArrayList<String>();
	
	static int deep = 0;
	
	public static void main(String[] argv) throws UnsupportedEncodingException, ParserException{  
		linkLists.add("http://m.lanmao.com/lmweChat/popularize/toPopularize");
		serverCrawler(linkLists,deep);
	} 
	
	
	public static void serverCrawler(List<String> urlLists,int deep) throws UnsupportedEncodingException, ParserException {
		linkLists = new ArrayList<String>();
		
		byte[] responseBody = null;
		for(String url : urlLists) {
			responseBody = readHtml(url);
			//下面用html解析网页的图片
			try {
				if(null != responseBody) {
					Parser parserImg = new Parser();  
					parserImg = Parser.createParser(new String(responseBody,"gb2312"),"gb2312");//原网站编码格式gb2312  
					NodeClassFilter filter1 = new 
							NodeClassFilter(ImageTag.class);//设置过滤器，这里的意思是设定具有class属性且属性值为new_table的过滤器  
					NodeList listImg = parserImg.extractAllNodesThatMatch(filter1);//抓取所有通过过滤器的网页DOM节点 
					writeImage(listImg,deep);
					
					//下面用html解析网页  链接
					Parser parserA = new Parser();  
					parserA = Parser.createParser(new String(responseBody,"gb2312"),"gb2312");//原网站编码格式gb2312  
					NodeClassFilter filter2 = new 
							NodeClassFilter(LinkTag.class);//设置过滤器，这里的意思是设定具有class属性且属性值为new_table的过滤器 
					NodeList listA = parserA.extractAllNodesThatMatch(filter2);//抓取所有通过过滤器的网页DOM节点 
					for(int i=0; i<listA.size(); i++) {
						LinkTag linkUrl = (LinkTag) listA.elementAt(i);
						linkLists.add(linkUrl.getLink());
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("这个url出错了url={}",url);
			}
		}
		
		if(deep<10) {
			serverCrawler(linkLists,deep+1);
		}
		
	}
	
	
	//存储图片
	public static void writeImage(NodeList list,int deep) {
		byte[] img = null;
		for (int i=0; i<list.size(); i++) {//基于设定的过滤器我知道抓取的是一个table元素，实际情况就需要你根据你想要的元素内容设置自己的过滤器（这是关键）  
			ImageTag image = (ImageTag) list.elementAt(i);  
			try {
				
				img = ImageUtils.getImageFromNetByUrl(image.getImageURL());
				if(null != img) {
					String[] fileNames =  image.getImageURL().split("\\.");
					
					File file = new File("C:\\" + "a\\"+deep+"-"+i + "." +fileNames[fileNames.length-1]);  
					FileOutputStream fops = new FileOutputStream(file);  
					fops.write(img);  
					fops.flush();  
					fops.close();  
					System.out.println("图片"+deep+"-"+i+"已经写入到C盘");  
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
	}
	
	//读取页面
	public static byte[] readHtml(String linkUrl) {
		byte[] responseBody = null;  
		if(null != linkUrl) {
			//连接http服务器端  
			HttpClient httpClient=new HttpClient();  
			GetMethod getMethod=new GetMethod(linkUrl);  
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
					System.out.println(new String(responseBody));  
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
		System.out.println("解析完成");
		return responseBody;
	}
} 


