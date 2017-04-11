/**
 * 
 */
package com.yeepay.g3.app.enums;

/**
 * @Description 结果类型（错误类型）
 * @author zhenping.zhou
 * @CreateTime 2016年7月28日 下午7:31:51
 * @version 1.0
 */
public enum ErrorCodeTypeEnum {
	/**
	 * 00000_请求成功
	 */
	LMAPP_REQUEST_SUCCESS,
	/**
	 * 11111_系统异常，请稍后重试
	 */
	LMAPP_NOT_KNOWN_ERROR,
	/**
	 * 10001_渠道请求不合法
	 */
	LMAPP_SRC_REQUEST_ERROR,
	/**
	 * 10002_用户名或密码为空
	 */
	LMAPP_NAMEORPWD_NULL_ERROR,
	/**
	 * 10003_该会员不存在
	 */
	LMAPP_MEMBER_NOT_EXIST_ERROR,
	/**
	 * 10004_登录密码不正确
	 */
	LMAPP_LOGINPWD_ERROR,
	/**
	 * 10005_手机号或操作类型为空
	 */
	LMAPP_TELOROPE_NULL_ERROR,
	/**
	 * 10006_手机号已经在平台注册
	 */
	LMAPP_MOBILENO_EXIST_ERROR,
	/**
	 * 10007_交易密码为空
	 */
	LMAPP_TRADEPWD_NULL_ERROR,
	/**
	 * 10008_交易密码和登录密码相同
	 */
	LMAPP_TRADEPWD_EQUALS_LOGINPWD_ERROR,
	/**
	 * 10009_身份证号为空
	 */
	LMAPP_IDCARD_NULL_ERROR,
	/**
	 * 10010_有银行卡信息不允许绑卡
	 */
	LMAPP_BANKCARD_EXIST_ERROR,
	/**
	 * 10011_身份证信息不正确，不是自己的，不允许绑卡
	 */
	LMAPP_IDCARD_ERROR,
	/**
	 * 10012_身份证已经在平台注册
	 */
	LMAPP_IDCARD_EXIST_ERROR,
	/**
	 * 10013_短信验证码输入错误
	 */
	LMAPP_MSGCODE_ERROR,
	/**
	 *10014_ 验证手机短信验证码异常
	 */
	LMAPP_VERIFY_MSGCODE_EXCEPTION,
	/**
	 * 10015_绑卡失败
	 */
	LMAPP_BANKCARD_ERROR,
	/**
	 * 10016_验证码或交易密码为空
	 */
	LMAPP_CODE_OR_TRADEPWD_NULL_ERROR,
	/**
	 * 10017_交易密码验证不通过
	 */
	LMAPP_VERIFY_TRADEPWD_ERROR,
	/**
	 * 10018_交易密码验证异常
	 */
	LMAPP_VERIFY_TRADEPWD_EXCEPTION,
	/**
	 * 10019_注册失败
	 */
	LMAPP_REGISTER_ERROR,
	/**
	 *10020_ 用户未登录或登录失效
	 */
	LMAPP_LOGIN_INVALID_ERROR,
	/**
	 * 10021_修改手机号失败
	 */
	LMAPP_MODIFY_TEL_FAILED_ERROR,
	/**
	 * 10022_修改手机号异常
	 */
	LMAPP_MODIFY_TEL_EXCEPTION,
	/**
	 * 10023_验证码或手机号为空
	 */
	LMAPP_TELORCODE_NULL_ERROR,
	/**
	 * 10024_请求不合理，有请求参数为空
	 */
	LMAPP_REQUEST_PARAM_NULL_ERROR,
	/**
	 * 10025_两次新交易密码不相同
	 */
	LMAPP_CONFIRM_TRADEPWD_ERROR,
	/**
	 * 10026_重置的交易密码与原密码相同
	 */
	LMAPP_RESET_TRADEPWD_EQUALS_PERPWD,
	/**
	 * 10027_新交易密码与登录密码一致
	 */
	LMAPP_NEWTRADEPWD_EQUALS_LOGINPWD,
	/**
	 * 10028_身份证信息验证错误
	 */
	LMAPP_VERIFY_IDCARD_ERROR,
	/**
	 *10029_验证交易密码与登录密码是否一致时异常
	 */
	LMAPP_VERIFY_TRADEPWD_LOGINPWD_ERROR,
	/**
	 *10030_修改交易密码时异常
	 */
	LMAPP_MODIFY_TRADEPWD_EXCEPTION,
	/**
	 * 10031_重置交易密码时异常
	 */
	LMAPP_RESET_TRADEPWD_ERROR,
	/**
	 * 10032_验证原登录密码时异常
	 */
	LMAPP_VERIFY_PER_LOGINPWD_ERROR,
	/**
	 * 10034_两次新登录密码不相同
	 */
	LMAPP_CONFIRM_LOGINPWD_ERROR,
	/**
	 * 10035_修改登录密码时异常
	 */
	LMAPP_MODIFY_LOGINPWD_EXCEPTION,
	/**
	 * 10036_重置登录密码时异常
	 */
	LMAPP_RESET_LOGINPWD_ERROR,
	/**
	 * 10037_userSessionKey不合法
	 */
	LMAPP_USERSESSIONEY_ERROR,
	
	/**
	 * 10039_添加用户反馈时异常
	 */
	LMAPP_ADD_USERSUGGESTION_ERROR,
	/**
	 * 10040_反馈意见为空，请正确输入反馈意见
	 */
	LMAPP_USERSUGGESTION_NULL_ERROR,
	
	/**
	 * 10041_用户尚未绑卡
	 */
	LMAPP_USER_NO_BANKCARD_ERROR,
	/**
	 * 10042_查询用户是否绑卡时异常
	 */
	LMAPP_VERIFY_ISBINDCARD_ERROR,
	/**
	 * 10043_验证身份证是否已经注册时异常
	 */
	LMAPP_VERIFY_IDCARD_ISREGISTER_ERROR,
	/**
	 * 10044-绑卡时出现异常
	 */
	LMAPP_BIND_CARD_EXCEPTION,
	/**
	 * 10045_验证时出现异常，请检查卡号是否输入正确
	 */
	LMAPP_VERIFY_CARD_TYPE_EXCEPTION,
	/**
	 * 10046_广告页无效或过期
	 */
	LMAPP_QUERY_AD_ZERO_NUM_EXCEPTION,
	/**
	 * 10047_请输入您首次绑卡的姓名及身份证号
	 */
	LMAPP_BIND_OTHER_CARD_ERROR,
	/**
	 * 10048_请输入正确的短信验证码
	 */
	LMAPP_MSGCODE_SHORT,
	/**
	 * 10049_发送短信验证码异常
	 */
	LMAPP_SEND_MSG_EXCEPTION,
	
	/**
	 * 10050_请输入正确的手机号
	 */
	LMAPP_SENDMSG_MOBILENO_ERROR,
	/**
	 * 10051_请输入中文姓名
	 */
	LMAPP_SENDMSG_REALNAME_ERROR,
	/**
	 * 10052_请输入正确的身份证号
	 */
	LMAPP_SENDMSG_IDCARD_ERROR,
	/**
	 * 10053_请输入正确的银行卡号
	 */
	LMAPP_SENDMSG_CARDNO_ERROR,
	/**
	 * 10054_请先设置交易密码
	 */
	LMAPP_SENDMSG_TRADEPWD_NULL,
	/**
	 * 10055_交易密码必须为8-20位数字和字母组合
	 */
	LMAPP_SENDMSG_TRADEPWD_ERROR,
	/**
	 * 10056_系统升级中暂停服务
	 */
	LMAPP_SYSTEM_UPGRADE,
}
