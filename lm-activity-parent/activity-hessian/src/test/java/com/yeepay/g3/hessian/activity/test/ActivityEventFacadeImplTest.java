package com.yeepay.g3.hessian.activity.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeepay.g3.facade.activity.dto.ActivityCouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityEventDTO;
import com.yeepay.g3.facade.activity.facade.ActivityCouponFacade;
import com.yeepay.g3.facade.activity.facade.ActivityEventFacade;
import com.yeepay.g3.hessian.activity.util.ApplicationContextUtil;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * @Description 
 * @author zhenping.zhou
 * @CreateTime 2015年12月7日 上午10:42:15
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:activity-core-spring/applicationContext.xml"})  
public class ActivityEventFacadeImplTest {

	@Resource
	private ActivityEventFacade activityEventFacadeImpl;
	
    @Before
    public void setUp() throws Exception {
    	
		RemoteServiceFactory.init();

        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
        activityEventFacadeImpl = (ActivityEventFacade) ctx.getBean("activityEventFacadeImpl");
//        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    }


    /**
     * 测试修改活动事件
     * @throws Exception
     */
    @Test
    public void testUpdateEvent() throws Exception {
    	ActivityEventDTO eventDto = new ActivityEventDTO();
    	eventDto.setId(Long.valueOf(42));
    	eventDto.setEventCode("LY_TEST");
    	eventDto.setEventName("测试更新");
    	eventDto.setCreateTime(new Date());
    	eventDto.setCreator("ying.liu");
    	String coupons = "62,63";
    	activityEventFacadeImpl.updateEvent(eventDto, coupons);
    }
}
