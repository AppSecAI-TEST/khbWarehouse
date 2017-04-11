package com.yeepay.g3.app.lmweChat.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.lanmao.consultant.facade.enumtype.Enums.UserType;
import com.yeepay.g3.facade.lmportal.dto.ChannelDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.enumtype.ChannelTypeEnum;
import com.yeepay.g3.facade.lmportal.service.ChannelFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.encrypt.Digest;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.smartcache.utils.SmartCacheUtils;


public class AppUtils {
	private static ChannelFacade channelFacade = RemoteServiceFactory.getService(ChannelFacade.class);
	//用户身份对应动作--线下理财顾问
	public static Map<Short, String> userTypeDesc = new HashMap<Short, String>();
	static{
		userTypeDesc.put(null, "成为推荐人");
		userTypeDesc.put(UserType.NONE.getValue(), "成为推荐人");
		userTypeDesc.put(UserType.USER.getValue(), "成为推荐人");
		userTypeDesc.put(UserType.REFEREES.getValue(),"我的推荐");
		userTypeDesc.put(UserType.ADVISER.getValue(), "我的客户");
		userTypeDesc.put(UserType.SALE.getValue(), "我的顾问");
		
	}
	public static final String SOURCE_SYSTEM = "lanmaoWx";
	
	
	public static MemberDto isLogin(String loginName, String srcNo,String userSessionKey){
		if(StringUtils.isNotEmpty(loginName)&&StringUtils.isNotEmpty(srcNo)&&StringUtils.isNotEmpty(userSessionKey)){
			//从redis查询是否有该渠道
			ChannelDto channelDto = null;
			MemberDto memberDto=null;
			Object channelObj = SmartCacheUtils.get(srcNo);
			if(channelObj == null) {
				channelDto = channelFacade.queryChannelByChannelNo(srcNo,ChannelTypeEnum.APP);
				if (channelDto == null) {
					return null;
				}
				SmartCacheUtils.set(srcNo, channelDto, LmConstants.REDIS_EXPIRE_TIME); //设置有效期
				
			} else {
				channelDto = (ChannelDto) channelObj;
			}
			if(null!=channelDto){
				// 由srcNo+loginName+sign计算signStr作为存入redis的member信息，类似于session
				String newUserSessionKey = Digest.md5Digest(srcNo + loginName
						+ channelDto.getSign());
				//身份验证通过
				if(null!=userSessionKey&&newUserSessionKey.equals(userSessionKey)){
					memberDto = (MemberDto) SmartCacheUtils
							.get(newUserSessionKey);
					return memberDto;
			}
		
		}
		}
		return null;
	}
}
