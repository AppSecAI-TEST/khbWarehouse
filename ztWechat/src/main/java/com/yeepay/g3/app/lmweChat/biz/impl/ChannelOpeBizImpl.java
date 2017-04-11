package com.yeepay.g3.app.lmweChat.biz.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.biz.ChannelOpeBiz;
import com.yeepay.g3.app.lmweChat.utils.LmConstants;
import com.yeepay.g3.facade.lmportal.dto.ChannelDto;
import com.yeepay.g3.facade.lmportal.enumtype.ChannelTypeEnum;
import com.yeepay.g3.facade.lmportal.service.ChannelFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.smartcache.utils.SmartCacheUtils;
/**
 * @Title: 渠道操作业务逻辑处理接口
 * @Description: 查询渠道
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-8-1 上午9:07:44
 * @version 2016-8-1
 */
@Component
public class ChannelOpeBizImpl implements ChannelOpeBiz {

	private ChannelFacade channelFacade = RemoteServiceFactory.getService(ChannelFacade.class);
	
	private static final Logger logger = LoggerFactory.getLogger(ChannelOpeBizImpl.class);
	@Override
	public ChannelDto getChannelInfo(String srcNo) {
		//先判断渠道是否合法，渠道信息存入redis，每日更新一次
		ChannelDto channelDto = null;
		if (StringUtils.isNotEmpty(srcNo)) {
			
			//从redis查询是否有该渠道
			Object channelObj = SmartCacheUtils.get(srcNo);
			if(channelObj == null) {
				channelDto = channelFacade.queryChannelByChannelNo(srcNo,ChannelTypeEnum.APP);
				if (channelDto == null) {
					//当查询出为空，说明已经失效或关闭
					logger.info("channelDto is null , srcNo={} " + srcNo);
					return null;
				}
				SmartCacheUtils.set(srcNo, channelDto, LmConstants.REDIS_EXPIRE_TIME); //设置有效期
				
			} else {
				channelDto = (ChannelDto) channelObj;
			}
		
		}else{
			logger.error("[getChannelInfo] ERROR={}", "渠道参数为空");
		}
		return channelDto;
	}
}
