/**
 * 
 */
package com.yeepay.g3.core.activity.service;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ImportRedPacketDTO;

/**
 * @Description 投资红包信息对外服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午8:18:56
 * @version 1.0
 */
public interface ActivityUserPacketService {
	
	/**
	 * 批量导入会员投资红包
	 * @param redPacketDtoList
	 */
	public void saveBatchUserPacket(List<ImportRedPacketDTO> redPacketDtoList);
}
