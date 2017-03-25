package com.yeepay.g3.hessian.activity.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeepay.g3.core.activity.dao.ActivityUserMessageDao;
import com.yeepay.g3.core.activity.entity.ActivityUserMessage;
import com.yeepay.g3.facade.activity.dto.ActivityCouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUserMessageDTO;
import com.yeepay.g3.facade.activity.enums.UserMessageReadStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityCouponFacade;
import com.yeepay.g3.facade.activity.facade.ActivityUserMessageFacade;
import com.yeepay.g3.utils.common.json.JSONObject;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * @Description 用户消息单元测试类
 * @author zhenping.zhou
 * @CreateTime 2015年12月7日 上午10:42:15
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:activity-core-spring/applicationContext.xml"})  
public class ActivityUserMessageFacadeImplTest {

	@Resource
	private ActivityUserMessageFacade activityUserMessageFacadeImpl;

	@Autowired
	private ActivityUserMessageDao activityUserMessageDaoImpl;

    @Before
    public void setUp() throws Exception {
    	
		RemoteServiceFactory.init();

//        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
        //activityCouponFacadeImpl = (ActivityCouponFacade) ctx.getBean("activityCouponFacadeImpl");
//        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    }

    /**
     * 测试查询用户消息列表
     * @throws Exception
     */
    @Test
    public void testSelectUserMessageList() throws Exception {
//        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);

    	String memberNo = "811234568100";
        List<ActivityUserMessageDTO> userMsgList = activityUserMessageFacadeImpl.selectByMemberNo(memberNo);
    }
    
    /**
     * 测试创建用户消息公告记录
     */
//    @Test
    public void testInsertUserMessage() throws Exception {
    	List<ActivityUserMessage> userMessageList = new ArrayList<ActivityUserMessage>();
    	ActivityUserMessage userMsg = new ActivityUserMessage();
    	userMsg.setMemberNo("123456");
		JSONObject msgParam = new JSONObject();
		msgParam.put("createTime", new Date());
		msgParam.put("score", "1");
		msgParam.put("wxNickName", "Iren08");
		msgParam.put("memberTel", "18810100000");
		msgParam.put("occurTime", new Date());
		userMsg.setMsgContent(msgParam.toString());
		userMsg.setOccurTime(new Date());
		userMsg.setReadStatus(UserMessageReadStatusEnum.UNREAD);
//		userMsg.setTemplateName("tjr_receive_raffle_ticket_4bindcard.ftl");
//		userMsg.setTemplateName("btjr_receive_raffle_ticket_4bindcard.ftl");
//		userMsg.setTemplateName("tjr_receive_raffle_ticket_4invest.ftl");
//		userMsg.setTemplateName("btjr_receive_raffle_ticket_4invest.ftl");
//		userMsg.setTemplateName("tjr_receive_score_4bindcard.ftl");
//		userMsg.setTemplateName("btjr_receive_score_4bindcard.ftl");
//		userMsg.setTemplateName("tjr_receive_score_4invest.ftl");
		userMsg.setTemplateName("btjr_receive_score_4invest.ftl");
    	userMessageList.add(userMsg);
    	
    	activityUserMessageDaoImpl.insertUserMessage(userMessageList);
    }
}
