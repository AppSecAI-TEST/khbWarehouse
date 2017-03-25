package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityUserMessageDTO;

/**
 * 
 * @Description 用户消息业务逻辑处理接口类
 * @author zhenping.zhou
 * @CreateTime 2016年6月4日 下午2:24:22
 * @version 1.0
 */
public interface ActivityUserMessageFacade {

	/**
     * 根据会员编号获取消息列表
     * @param memberNo
     * @return
     */
    public List<ActivityUserMessageDTO> selectByMemberNo(String memberNo);
    
    /**
     * 更改阅读状态
     * @author 陈大涛
     * 2016-6-7上午10:52:35
     */
    public void updateReadStatus(ActivityUserMessageDTO activityUserMessageDto);
    
    /**
     * 查询会员未读消息数量
     * @author 陈大涛
     * 2016-6-8下午3:08:52
     */
    public Integer queryMessageCountByMemberNo(String memberNo);
}