/**
 * 
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.facade.activity.dto.ImportRedPacketDTO;

/**
 * @Description 投资红包信息对外服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午8:18:56
 * @version 1.0
 */
public interface ActivityUserPacketFacade {
	
	/**
	 * 批量导入会员投资红包
	 * @param redPacketDtoList
	 */
	public void batchAddUserPacket(List<ImportRedPacketDTO> redPacketDtoList);
	
	/**
	 * 根据批次号批量审核用户红包
	 * @param batchId
	 */
	public void userPacketCheck(Map<String, Object> params);
	
}
