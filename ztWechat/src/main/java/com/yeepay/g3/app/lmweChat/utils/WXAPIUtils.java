package com.yeepay.g3.app.lmweChat.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import com.yeepay.g3.utils.common.httpclient.SimpleHttpUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigurationUtils;

/**
 * 微信API工具类
 * 
 * @author junning.li
 * 
 */
public class WXAPIUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(WXAPIUtils.class);
	public static long time = -1;
	public static String jsapi_ticket = null;
	public static String access_token = null;
	//获取access_token
	protected static final String WX_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
	//发送模板消息
	protected static final String WX_SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send";
	//获取wx用户信息
	protected static final String WX_GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";
	public static final String APP_ID = ConfigurationUtils
			.getSysConfigParam("lanmaowx_appid").getValue().toString();
	public static final String APP_SECRET = ConfigurationUtils
			.getSysConfigParam("lanmaowx_appsecret").getValue().toString();


//	public static final String APP_ID="wx196f101900ebc50b";
//	public static final String APP_SECRET="d4624c36b6795d1d99dcf0547af5443d";
	/**
	 * 用来获取微信openId的请求参数
	 * @author ping.zhu
	 * @param code
	 * @return
	 */
	public static Map<String, String> buildGetWebAccessTokenParam(String code) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("appid", APP_ID);
		paramMap.put("secret", APP_SECRET);
		paramMap.put("grant_type", "authorization_code");
		paramMap.put("code", code);
		return paramMap;
	}
	/**
	 * 
	 * 用来获取微信用户信息的请求参数
	 * @author ping.zhu
	 * @param openId
	 * @return
	 */
	public static Map<String,String> buildGetUserInfoParam(String openId){
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("openid",openId);
		paramMap.put("lang","zh_CN");
		paramMap.put("ACCESS_TOKEN",getOrdinaryAccessToken());
		return paramMap;
	}

	/**
	 * 获取基础access_token；
	 * @author ping.zhu
	 * @return
	 */
	public static String getOrdinaryAccessToken() {
		// 凭证获取(GET)
		String token_url = WX_ACCESS_TOKEN+"?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		String requestUrl = token_url.replace("APPID",APP_ID).replace("APPSECRET",APP_SECRET);
		// 发起GET请求获取凭证
		String str = SimpleHttpUtils.httpGet(requestUrl, null);
		JSONObject jsonObject = JSONObject.fromObject(str);
		String access_token = null;
		if (null != jsonObject) {
			access_token = jsonObject.getString("access_token");
		}
		return access_token;
	}

	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static String getAccessToken(String appid, String appsecret) {
		// 凭证获取(GET)
		String token_url = WX_ACCESS_TOKEN+"?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		String requestUrl = token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		// 发起GET请求获取凭证
		
		String str = SimpleHttpUtils.httpGet(requestUrl, null);
		
		System.out.println(str);
		JSONObject jsonObject = JSONObject.fromObject(str);
		String access_token = null;
		if (null != jsonObject) {
			access_token = jsonObject.getString("access_token");
		}
		return access_token;
	}

	/**
	 * 调用微信JS接口的临时票据
	 * 
	 * @param access_token
	 *            接口访问凭证
	 * @return
	 */
	public static String getJsApiTicket(String accessToken) {
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		String requestUrl = url.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求获取凭证
		String str = SimpleHttpUtils.httpGet(requestUrl, null);
		JSONObject jsonObject = JSONObject.fromObject(str);
		String ticket = null;
		if (null != jsonObject) {
			ticket = jsonObject.getString("ticket");
		}
		return ticket;
	}

	public static String sha1Encrypt(String str) {
		String signature = null;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(str.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signature;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	// 获取当前系统时间 用来判断access_token是否过期
	public static String getTime() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}

	public static Map getParam(String appid, String appsecret, String url) {
		Map<String,Object> map = new HashMap<String, Object>();
		long now = System.currentTimeMillis();
		String randomStr = UUID.randomUUID().toString();
		if (time < 0 || now - time > 3600000) {
			time = now;
			access_token = getAccessToken(appid, appsecret);
			jsapi_ticket = getJsApiTicket(access_token);
		}

		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + randomStr
				+ "&timestamp=" + time + "&url=" + url;
		String signature = sha1Encrypt(str);

		map.put("time", time);
		map.put("randomStr", randomStr);
		map.put("signature", signature);
		map.put("accessToken", access_token);
		return map;
	}

//	public static void main(String[] args) {
//		Map map = getParam("wxe27267ccc8d4808e",
//				"7e63b6630406ca967d087e7dc7db4793",
//				"http://59.151.25.126:6325/lanmao-mobile/toLogin");
//		System.out.println(map);
//		String data = "{\"button\":[{\"type\":\"view\",\"name\":\"懒猫理财\",\"url\":\"http://59.151.25.126:6325/lanmao-mobile/toLogin\"}]}";
//
//		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
//				+ access_token;
//		SimpleHttpParam param = new SimpleHttpParam(url);
//		param.setMethod("POST");
//		param.setPostData(data);
//
//		SimpleHttpResult result = SimpleHttpUtils.httpRequest(param);
//		System.out.println(result.getContent());
//
//	}
	public static void main(String args[]){
		String str="{'access_token':'tFmhsuui945f2N5wcGgnDBXNCfoSSYF_RC7HwPvvMbdN53gPFlsGWO8GADVInf5KQ9nthrLkK6eXaN2LMMKm8TWE9wn0YF30jp-bdv6LF6EGYDjABAQIN','expires_in':7200}";
		JSONObject jsonObject=JSONObject.fromObject(str);
		String s=jsonObject.getString("access_token");
		System.out.println(s);
	}
	private static void get(String string) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 发送消息模板（test）
	 * @author cdt
	 * @date 2016-5-23
	 * @time 下午3:59:05
	 */
	public static void sendWxMessage(Map<String,Object> param){
		String access_token=null;
		  //获取 access_token;
		access_token=getOrdinaryAccessToken();
		//发送模板信息
			String sendResult = HttpRequestUtils.post(WX_SEND_MESSAGE+"?access_token="+access_token,JSONObject.fromObject(param).toString());
			logger.info("[sendMessage] 发送消息模板：返回结果info={}",sendResult);
	}
	/**
	 * 消息模参数 暂时不用公共方法，可变参数太多
	 */
//	public String getSendMessageParam(String openId,WxTemplateIdEnum templateId){
//		 String data=""; String param="";
//		 switch (templateId) {
//		case EvBnvREVP6ie2CshbDQTJMIj7OKvdB5RgWKI6P9rjxM:
//			data="{\"first\": {\"value\":\"测试1\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"测试2\"" +
//			 		",\"color\":\"#173177\"},\"keyword2\": {\"value\":\"测试3\",\"color\":\"#173177\"},\"remark\": {\"value\":\"测试4\",\"color\":\"#173177\"}}";
//			param="{ \"touser\":\"" +
//			 		openId+"\",\"template_id\":\"EvBnvREVP6ie2CshbDQTJMIj7OKvdB5RgWKI6P9rjxM\",\"url\":\"www.baiduc.om\",\"data\":" +
//			 						data+"}";
//			break;
//
//		default:
//			break;
//		}
//		 
//		 
//		 return param;
//	}
	/**
	 * 获取wx用户的信息
	 * @author cdt
	 * @date 2016-5-24
	 * @time 上午11:40:04
	 */
	public static String getWxUserInfo(String openId){
		return HttpRequestUtils.sendHttpRequest(WX_GET_USER_INFO, "get", buildGetUserInfoParam(openId));
	}
}
