package com.xinnet.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





import org.springframework.util.StringUtils;

import com.xinnet.utils.ImageUtils;

public class CrawlerUtils{ 
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlerUtils.class);
	
	
	static List<String> linkLists = new ArrayList<String>();
	
	static int deep = 0;
	
	static int k = 0;
	
	static String pattern = "^((http://)|(https://)|(//))?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/)";
	static Pattern p = Pattern.compile(pattern);
	
	public static void main(String[] argv) throws UnsupportedEncodingException, ParserException{  
		linkLists.add("http://quote.eastmoney.com/sz002461.html?from=BaiduAladdin");
		serverCrawler(linkLists,deep);
	} 
	
	
	public static void serverCrawler(List<String> urlLists,int deep) throws UnsupportedEncodingException, ParserException {
		
		linkLists = new ArrayList<String>();
		
		byte[] responseBody = null;
		for(String url : urlLists) {
			if(StringUtils.isEmpty(url)) {
				continue;
			}
			responseBody = readHtml(url);
			//STEP.1  匹配域名
			
	        Matcher m = p.matcher(url);
	        String baseUrl = "";
	        if(m.find()){
	            //匹配结果
	        	baseUrl = m.group();
	        }
			//STEP.2  下面用html解析网页
			try {
				if(null != responseBody) {
					if(url.indexOf(".css") < 0 && url.indexOf(".js") < 0) {
						//STEP.3 存储页面
						try {  
							//过滤页面名称
							String fileName = String.valueOf(k);
							TagNameFilter titleFilter = new TagNameFilter("title");//设置过滤器，标签名称
							NodeList titlelist = filterTag(responseBody,titleFilter);//抓取所有通过过滤器的网页DOM节点 
							for(int i=0; i<titlelist.size(); i++) {
								TitleTag tag = (TitleTag)titlelist.elementAt(i);
								if(!StringUtils.isEmpty(tag) && !StringUtils.isEmpty(tag.getTitle())) {
									fileName = tag.getTitle();
									FileOutputStream fops = FileUtils.openOutputStream(new File("E:/crawler/html/" + fileName + ".html"),true);
									fops.write(responseBody);  
									fops.flush();  
									fops.close();
								}
							}
							k=k+1;
						} catch (Exception e) {  
							e.printStackTrace();  
						} 
					}
					
					if(url.toLowerCase().endsWith(".css")) {
						//STEP.4 存储css
						try {  
//							File file = new File("E:\\" + "crawler\\css\\" + url.substring(url.lastIndexOf("/")+1));  
//							FileOutputStream fops = new FileOutputStream(file); 
							FileOutputStream fops = FileUtils.openOutputStream(new File("E:/crawler/css/" + url.substring(url.lastIndexOf("/")+1)),true);
							fops.write(responseBody);  
							fops.flush();  
							fops.close();
						} catch (Exception e) {  
							e.printStackTrace();  
						} 
					}
					
					if(url.toLowerCase().endsWith(".js")) {
						//STEP.5 存储js
						try {  
//							File file = new File("E:\\" + "crawler\\js\\" + url.substring(url.lastIndexOf("/")+1));  
//							FileOutputStream fops = new FileOutputStream(file); 
							FileOutputStream fops = FileUtils.openOutputStream(new File("E:/crawler/js/" + url.substring(url.lastIndexOf("/")+1)),true);
							fops.write(responseBody);  
							fops.flush();  
							fops.close();
						} catch (Exception e) {  
							e.printStackTrace();  
						} 
					}
					
					//STEP.6 过滤图片
					NodeFilter filter1 = new NodeClassFilter(ImageTag.class);//设置过滤器，这里的意思是设定具有class属性且属性值为new_table的过滤器  
					NodeList listImg = filterTag(responseBody,filter1);
					writeImage(listImg,deep);
					
					//下面用html解析网页  链接
					NodeClassFilter filter2 = new NodeClassFilter(LinkTag.class);//设置过滤器，这里的意思是设定具有a标签
					NodeList listA =  filterTag(responseBody,filter2);//抓取所有通过过滤器的网页DOM节点 
					for(int i=0; i<listA.size(); i++) {
						LinkTag linkUrl = (LinkTag) listA.elementAt(i);
						if(p.matcher(linkUrl.getLink()).find()) {
							linkLists.add(linkUrl.getLink());
						} else {
							if(!StringUtils.isEmpty(baseUrl)) {
								linkLists.add(baseUrl + linkUrl.getLink());
							}
						}
					}
					
					//过滤css
					TagNameFilter cssLink = new TagNameFilter("link");//设置过滤器，标签名称
					NodeList csslist =  filterTag(responseBody,cssLink);//抓取所有通过过滤器的网页DOM节点 
					for(int i=0; i<csslist.size(); i++) {
						Tag cssUrl = (Tag) csslist.elementAt(i);
						if(cssUrl.getAttribute("href").indexOf(".css") > 0) {
							if(p.matcher(cssUrl.getAttribute("href")).find()) {
								linkLists.add(cssUrl.getAttribute("href"));
							} else {
								if(!StringUtils.isEmpty(baseUrl)) {
									linkLists.add(baseUrl + cssUrl.getAttribute("href"));
								}
							}
						}
					}
					
					//过滤js
					TagNameFilter jsFilter = new TagNameFilter("script");//设置过滤器，标签名称
					NodeList jslist = filterTag(responseBody,jsFilter);//抓取所有通过过滤器的网页DOM节点 
					for(int i=0; i<jslist.size(); i++) {
						Tag jsUrl = (Tag) jslist.elementAt(i);
						if(jsUrl.getAttribute("src").indexOf(".js") > 0) {
							if(p.matcher(jsUrl.getAttribute("src")).find()) {
								linkLists.add(jsUrl.getAttribute("src"));
							} else {
								if(!StringUtils.isEmpty(baseUrl)) {
									linkLists.add(baseUrl + jsUrl.getAttribute("src"));
								}
							}
						}
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
					
//					File file = new File("E:\\" + "crawler\\image\\"+image.getImageURL().substring(image.getImageURL().lastIndexOf("/")+1));  
//					FileOutputStream fops = new FileOutputStream(file);  
					FileOutputStream fops = FileUtils.openOutputStream(new File("E:/crawler/image/" + image.getImageURL().substring(image.getImageURL().lastIndexOf("/")+1)),true);
					fops.write(img);  
					fops.flush();  
					fops.close();  
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}  
		}
	}
	
	//读取页面
	public static byte[] readHtml(String linkUrl) {
		byte[] responseBody = null;  
		if(null != linkUrl) {
			/*try{
			    Thread thread = Thread.currentThread();
			    thread.sleep(2000);//暂停1.5秒后程序继续执行
			}catch (InterruptedException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}*/
			
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
//					System.out.println(new String(responseBody));  
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
	
	private static NodeList filterTag(byte[] responseBody, NodeFilter filter) throws UnsupportedEncodingException, ParserException {
//		Parser parser = new Parser(new String(responseBody)); 
//		// 设置编码 
//		parser.setEncoding("gbk"); 
		Parser parser = new Parser();  
		parser = Parser.createParser(new String(responseBody,"utf-8"),"utf-8");//原网站编码格式gb2312  
		NodeList list = parser.extractAllNodesThatMatch(filter);
		return list;
	}
} 


