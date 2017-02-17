package com.xinnet.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 加密、解密操作
 * @author hongbin.kang
 * @date 2016年9月5日上午11:12:19
 */
public class EncryptUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(EncryptUtils.class);

	/**
	 * url地址的特殊字符进行转换
	 * @author hongbin.kang
	 * @param str
	 * @return
	 */
	public static String encodeURI(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
		}
		return str;
	}

	/**
	 * url地址转换为原本的地址
	 * @author hongbin.kang
	 * @param str
	 * @return
	 */
	public static String decodeURI(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (Exception e) {
		}
		return str;
	}

	/**
	 * md5的16为加密
	 * @author hongbin.kang
	 * @param str
	 * @return
	 */
	public static String encodeMD5(String str) {
		String temp = encodemd5(str);
		if (temp != null) {
			return temp.substring(8, 24);
		}
		return temp;
	}

	/**
	 * md5 获取16位的数据
	 * @author hongbin.kang
	 * @param str
	 * @return
	 */
	public static String encodemd5(String str) {
		MessageDigest md = null;
		String dstr = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] b = md.digest();
			StringBuffer buf = new StringBuffer("");
			for (int i : b) {
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			dstr = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return dstr;
	}

	/**
	 * Des加密
	 * @author hongbin.kang
	 * @param strIn 要加密的数据
	 * @return
	 */
	public static String encodeDes(String strIn) {
		try {
			Cipher cipher = getDesCipher("encrypt");
			return byteArr2HexStr(cipher.doFinal(strIn.getBytes()));
		} catch (Exception e) {
			logger.error("加密DES[" + strIn + "]失败", e);
		}
		return null;
	}
	
	
	/**
	 * Des解密
	 * @author hongbin.kang
	 * @param strIn 要解密的数据
	 * @return
	 */
	public static String decodeDes(String strIn) {
		try {
			Cipher cipher = getDesCipher("decode");
			return new String(cipher.doFinal(hexStr2ByteArr(strIn)));
		} catch (Exception e) {
			logger.error("解密DES[" + strIn + "]失败", e);
		}
		return null;
	}
	
	
	/**
	 * Des加密
	 * @author hongbin.kang
	 * @param key 密钥
	 * @param strIn 要加密的数据
	 * @return
	 */
	public static String encodeDes(String key, String strIn){
		try{
			Cipher cipher = getDesCipher(key,"encrypt");
			return byteArr2HexStr(cipher.doFinal(strIn.getBytes("utf-8")));
		} catch (Exception e){
			logger.error("加密DES[" + strIn + "]失败", e);
		}
		
		return null;
	}

	
	/**
	 * Des解密
	 * @author hongbin.kang
	 * @param key 密钥
	 * @param strIn 要解密的数据
	 * @return
	 */
	public static String decodeDes(String key, String strIn) {
		try {
			Cipher cipher = getDesCipher(key, "decode");
			return new String(cipher.doFinal(hexStr2ByteArr(strIn)));
		} catch (Exception e) {
			logger.error("解密DES[" + strIn + "]失败", e);
		}
		return null;
	}
	
	/**
	 * des的加密解密
	 * @author hongbin.kang
	 * @param key 密钥
	 * @param type
	 * @return
	 */
	private static Cipher getDesCipher(String key, String type) {
		Cipher cipher = null;
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

			SecretKey secretKey = keyFactory.generateSecret(dks);
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			if ("encrypt".equals(type))
				cipher.init(1, secretKey);
			else
				cipher.init(2, secretKey);
		} catch (Exception e) {
			logger.error("初始化DES-Cipher失败", e);
		}
		return cipher;
	}

	/**
	 * 获取默认的des的key的加密解密
	 * @author hongbin.kang
	 * @param type
	 * @return
	 */
	private static Cipher getDesCipher(String type) {
		Cipher cipher = null;
		try {
			DESKeySpec dks = new DESKeySpec("*&_kanghb^_^360_&".getBytes());

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

			SecretKey secretKey = keyFactory.generateSecret(dks);
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			if ("encrypt".equals(type))
				cipher.init(1, secretKey);
			else
				cipher.init(2, secretKey);
		} catch (Exception e) {
			logger.error("初始化DES-Cipher失败", e);
		}
		return cipher;
	}

	/**
	 * des对数据加密
	 * @author hongbin.kang
	 * @param arrB
	 * @return
	 * @throws Exception
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp += 256;
			}
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16).toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * des对数据解密
	 * @author hongbin.kang
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	private static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i += 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[(i / 2)] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	/**
	 * MD5 加密算法
	 * @author hongbin.kang
	 * @param sign
	 * @return
	 */
	public static String MD5(String sign) {
		
		byte[] bytes = null;
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			bytes = md5.digest(sign.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// 将MD5输出的二进制结果转换为小写的十六进制
		StringBuilder sign_s = new StringBuilder();
		
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			
			if (hex.length() == 1){
				sign_s.append("0");
			}
			sign_s.append(hex);     
		}
		return sign_s.toString();
	}
	
}
