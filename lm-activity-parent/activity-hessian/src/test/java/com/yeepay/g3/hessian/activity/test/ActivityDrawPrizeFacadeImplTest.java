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

import com.yeepay.g3.core.activity.facade.impl.ActivityDrawPrizeFacadeImpl;
import com.yeepay.g3.facade.activity.dto.ActivityPrizeDTO;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageDTO;
import com.yeepay.g3.facade.activity.enums.ActivityWXSendMessageEnum;
import com.yeepay.g3.facade.activity.facade.ActivityDrawPrizeFacade;
import com.yeepay.g3.hessian.activity.util.ApplicationContextUtil;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * @author 陈大涛
 * 2016-6-1下午1:35:23
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:activity-core-spring/applicationContext.xml"})  
public class ActivityDrawPrizeFacadeImplTest {
	
	@Resource
	private ActivityDrawPrizeFacade activityDrawPrizeFacadeImpl;
	  @Before
	    public void setUp() throws Exception {
	    	
			RemoteServiceFactory.init();
			 ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
			 activityDrawPrizeFacadeImpl = (ActivityDrawPrizeFacadeImpl) ctx.getBean("activityDrawPrizeFacadeImpl");

//	        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
	        //activityCouponFacadeImpl = (ActivityCouponFacade) ctx.getBean("activityCouponFacadeImpl");
//	        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
	    }
	  @Test
	  public void drawPrize() throws Exception {
		  String memberNo="811234568021";
		  String actionCode="007";
		  String activityCode="007";
//		  String openId="";
		  ActivityPrizeDTO result = activityDrawPrizeFacadeImpl.updateUserPrize(memberNo, actionCode, activityCode);
		  System.out.println(result);
	  }
}
