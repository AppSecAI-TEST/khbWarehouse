package com.yeepay.g3.app.lmweChat.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.net.URLCodec;

import net.sf.json.JSONObject;

import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.common.httpclient.SimpleHttpUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigurationUtils;

/**
 * 加密解密的utils
 * @author hongbin.kang
 * @date 2016年8月23日下午3:19:35
 */
public class SecretUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(SecretUtils.class);
	
	protected static String memberKey = "kang!@#lanmaoghb";
	
	/**
	 * 对数据加密
	 * @author hongbin.kang
	 * @date 2016年8月23日 下午3:26:29
	 * @param data
	 * @return
	 */
	public static String secretData(String data) {
		logger.info("加密前数据data={}",data);
		if(!StringUtils.isEmpty(data)) {
			data = AES.encryptToBase64(data, memberKey);
			logger.info("加密后数据data={}",data);
			try {
				data = URLEncoder.encode(data, "UTF-8");
				logger.info("URLEncoder数据data={}",data);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return data;
		}
		return null;
	}
	
	/**
	 * 对数据进行解密
	 * @author hongbin.kang
	 * @date 2016年8月23日 下午3:27:22
	 * @param data
	 * @return
	 */
	public static String unSecretData(String data) {
		logger.info("解密前数据data={}",data);
		if(!StringUtils.isEmpty(data)) {
			data = AES.decryptFromBase64(data, memberKey);
			logger.info("解密后数据data={}",data);
			return data;
		}
		return null;
	}
	
	
}
