package com.yeepay.g3.app.lmweChat.biz;

import com.yeepay.g3.facade.lmportal.dto.ChannelDto;

/**
 * @Title: 渠道操作业务逻辑处理
 * @Description: 渠道操作业务逻辑
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-29 下午5:11:58
 * @version 2016-7-29
 */

public interface ChannelOpeBiz {
	
	/**
	 * 获取渠道信息
	 * @param in -
	 * @return 
	 */
	public ChannelDto getChannelInfo(String srcNo);
}
