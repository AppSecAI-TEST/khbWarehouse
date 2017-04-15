package com.xinnet.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * property文件读取工具类
 * @author hongbin.kang
 */
public class PropertiesUtils {
	
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	
	private static Properties property = new Properties();  
	/**
	 * 不指定文件，统一读取此文件里面的配置
	 * 只在调用时加载一次
	 */
	static {
		InputStream inStream = null;  
		try {  
			//resources目录下
			inStream = PropertiesUtils.class.getResourceAsStream("/lanmao.properties");  
			//得到当前类的路径，并把资源文件名作为输入流  
			property.load(inStream);  
		} catch (IOException e) {  
			e.printStackTrace(); 
			logger.info("读取资源文件出错");
		} finally {  
			try {  
				if (null != inStream) {  
					inStream.close();  
				}  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}  
	}
	
	/**
	 * 读取配置文件里面的value值
	 * @author hongbin.kang
	 * @param key 
	 *         键
	 * @return
	 */
	public static String getPropertyValue(String key) {
		if(null != key) {
			if(!StringUtils.isEmpty(property.getProperty(key))){
				return property.getProperty(key);
			}
		}
		logger.info("[getPropertyValue]的key的value不存在，key={}",key);
		return null;
	}
	
	/**
	 * 读取指定文件下的value值
	 * @author hongbin.kang
	 *
	 * @param path 
	 *          资源文件
	 * @param key
	 *          键
	 * @return
	 */
	public static String getPropertyValue(String path,String key) {
		if(null == key || null == path) {
			return null;
		}
		InputStream in = null;  
		Properties pros = new Properties();  
		try {  
			//resources目录下
			in = PropertiesUtils.class.getResourceAsStream(path);  
			//得到当前类的路径，并把资源文件名作为输入流  
			pros.load(in);
			if(!StringUtils.isEmpty(pros.getProperty(key))) {
				return pros.getProperty(key);
			}
			logger.info("[getPropertyValue]-path路径-key的value不存在,path={},key={}",path,key);
		} catch (IOException e) {  
			e.printStackTrace(); 
			logger.info("读取资源文件出错");
		} finally {  
			try {  
				if (null != in) {  
					in.close();  
				}  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}  
		return null;
	}
}

