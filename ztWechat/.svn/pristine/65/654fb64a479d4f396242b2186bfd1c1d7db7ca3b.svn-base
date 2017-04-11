package com.yeepay.g3.app.lmweChat.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lanmao.consultant.facade.enumtype.Enums.UserType;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.SecretUtils;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.emvc.ActionSupport;
public class BaseAction extends ActionSupport{
	
	protected static Map<String,Object> urlMap = new HashMap<String,Object>();
	
	protected static Map<String, String> bankMap = new HashMap<String, String>();
	//用户身份对应动作--线下理财顾问
	protected static Map<Short, String> userTypeDesc = new HashMap<Short, String>();
	
	protected static String testMemberNo = "811234568316";
	
	protected static String memberKey = "kang!@#lanmaoghb";
	
	static{
		bankMap.put("ICBC", "中国工商银行");
		bankMap.put("ABC", "中国农业银行");
		bankMap.put("BOC","中国银行");
		bankMap.put("CCB", "中国建设银行");
		bankMap.put("CEB", "中国光大银行");
		bankMap.put("CIB", "兴业银行");
		bankMap.put("CMBC", "中国民生银行");
		bankMap.put("HX", "华夏银行");
		bankMap.put("ECITIC", "中信银行");
		bankMap.put("GDB", "广东发展银行");
	}
	
	static{
		userTypeDesc.put(null, "成为推荐人");
		userTypeDesc.put(UserType.NONE.getValue(), "成为推荐人");
		userTypeDesc.put(UserType.USER.getValue(), "成为推荐人");
		userTypeDesc.put(UserType.REFEREES.getValue(),"我的推荐");
		userTypeDesc.put(UserType.ADVISER.getValue(), "我的客户");
		userTypeDesc.put(UserType.SALE.getValue(), "我的顾问");
		
	}
	
	protected static final String NULL_RESULT = null;
	
	protected static final String NULL_RESULT_JSON = "\"NULL_RESULT\"";
		
	protected static final String SOURCE_SYSTEM = "lanmaoWx";

	protected static final int PAGE_SIZE = 10;
	
	protected static final int PAGE_SIZE_MAX = 9999;//查询全部极大数

	protected static final String WX_OAUTH_REUQEST_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
//	protected static final String WX_

	protected static final String WX_OAUTH_REUQEST_METHOD = "get";
	
	protected static final Long DOUBLE_ACTIVITY_ID = 1l;

	protected static final Long CAMPUS_ACTIVITY_ID = 2l;
	
	//验证码失败
	protected static final String VERIFY_FAILED = "VERIFY_FAILED";
	//手机验证码验证失败
	protected static final String DYNAMIC_FAILED = "DYNAMIC_FAILED";
	//失败
	protected static final String FAILED = "FAILED";
	//系统异常或网络延迟
	protected static final String SYSTEM_EXCEPTION = "SYSTEM_EXCEPTION";
	//系统异常或网络延迟 json
	protected static final String SYSTEM_EXCEPTION_JSON = "\"SYSTEM_EXCEPTION\"";
	//已经注册过
	protected static final String REGISTERED = "REGISTERED";
	//身份证与首次绑卡时不符
	protected static final String IDCARDERROR = "IDCARDERROR";
	//错误密码
	protected static final String WRONG_PWD = "WRONG_PWD";
	//交易密码核查限制
	protected static final String TRADE_PWD_VERIFY_LIMIT = "TRADE_PWD_VERIFY_LIMIT";
	//密码矛盾不一致
	protected static final String PWD_INCONSISTENT = "PWD_INCONSISTENT";
	//失败
	protected static final String PAYMENT_LIMIT = "PAYMENT_LIMIT";
	//用户不存在
	protected static final String USER_NOTEXIST = "USER_NOTEXIST";
	//未登录，用户第一次进入微信端
	protected static final String MLANMAO_IS_FIRST_IN = "MLANMAO_IS_FIRST_IN";
	//用户登陆，说明与公众号首次交互 弹出蒙层
	protected static final String MLANMAO_IS_FIRST_Popularize = "MLANMAO_IS_FIRST_Popularize";
	//信托购买页 首次交互 弹出蒙层
	protected static final String MLANMAO_IS_FIRST_FIXED = "MLANMAO_IS_FIRST_FIXED";
	//统一配置——数据字典
	protected static final String CONFIG_TYPE_TEXT_RESOURCES="config_type_text_resources";
	//统一配置——产品详情参数
	protected static final String LMWX_PRODUCT_INFO_PARAM="lmwx_product_info_param";
	//OPENID存入cookie键
	protected static final String MLANMAO_OPEN_ID = "MLANMAO_OPEN_ID";
	
	/**
	 * 根据member快速登录，基金过来
	 * @author hongbin.kang
	 * @date 2016年8月20日 下午7:42:12
	 * @param memberNo
	 * @param request
	 * @return
	 */
	protected MemberManagementFacade memberManagementFacade = RemoteServiceFactory
			.getService(MemberManagementFacade.class);
	protected Boolean setSessionByFundSales(String memberNo,HttpServletRequest request) {
		logger.info("快速登录，基金过来 memberNo={}",memberNo);
		HttpSession session = request.getSession();
		if(null != memberNo && !"".equals(memberNo) && !"null".equalsIgnoreCase(memberNo)) {
			memberNo = SecretUtils.unSecretData(memberNo);
//			memberNo = AES.decryptFromBase64(memberNo, memberKey);
			logger.info("快速登录，基金过来 memberNo={}",memberNo);
			MemberDto memberDto = memberManagementFacade.obtainMember(memberNo);
			if (null != memberDto) {
				session.setAttribute("member",memberDto);
				return true;
			}
		}
		logger.info("快速登录，用户的平台来源platform={}",request.getParameter("platform"));
		if(null != request.getParameter("platform")) {
			session.setAttribute("platfrom", request.getParameter("platform"));
		}
		return false;
	}
}
