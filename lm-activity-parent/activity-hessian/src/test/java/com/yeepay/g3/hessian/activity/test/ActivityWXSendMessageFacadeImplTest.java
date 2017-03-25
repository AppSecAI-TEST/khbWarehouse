/**
 * @author 陈大涛
 * 2016-6-1下午1:35:23
 */
package com.yeepay.g3.hessian.activity.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageDTO;
import com.yeepay.g3.facade.activity.enums.ActivityWXSendMessageEnum;
import com.yeepay.g3.facade.activity.facade.ActivityEventFacade;
import com.yeepay.g3.facade.activity.facade.ActivityUsercouponFacade;
import com.yeepay.g3.facade.activity.facade.ActivityWXSendMessageFacade;
import com.yeepay.g3.hessian.activity.util.ApplicationContextUtil;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * @author 陈大涛
 * 2016-6-1下午1:35:23
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:activity-core-spring/applicationContext.xml"})  
public class ActivityWXSendMessageFacadeImplTest {
	
	@Resource
	private ActivityWXSendMessageFacade activityWXSendMessageFacadeImpl;
	  @Before
	    public void setUp() throws Exception {
	    	
			RemoteServiceFactory.init();
			 ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
			 activityWXSendMessageFacadeImpl = (ActivityWXSendMessageFacade) ctx.getBean("activityWXSendMessageFacadeImpl");

//	        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
	        //activityCouponFacadeImpl = (ActivityCouponFacade) ctx.getBean("activityCouponFacadeImpl");
//	        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
	    }
	  @Test
	  public void sendWXMessage(){
		  ActivityWXSendMessageDTO  paramDto = new ActivityWXSendMessageDTO ();
			paramDto.setFirst("测试中奖");
			paramDto.setRemark("中奖成功");
			paramDto.setKeyword1("抽奖大转盘");
			paramDto.setKeyword2("懒猫公仔一个");
			paramDto.setOpenId("ocJuwtzlmzg08SZS-iZkxiujHVUs");
			paramDto.setUrl("m.lanmao.com/lmweChat/asset/toAsset");
			activityWXSendMessageFacadeImpl.sendWxMessage(ActivityWXSendMessageEnum.GET_PRIZE, paramDto);
			activityWXSendMessageFacadeImpl.getWxUserInfo("ocJuwtzlmzg08SZS");
	  }
}
