package com.yeepay.g3.hessian.activity.test;

import java.text.SimpleDateFormat;
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
import com.yeepay.g3.facade.activity.dto.ActivityRuleDTO;
import com.yeepay.g3.facade.activity.facade.ActivityCouponFacade;
import com.yeepay.g3.facade.activity.facade.ActivityEventFacade;
import com.yeepay.g3.facade.activity.facade.ActivityRuleFacade;
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
public class ActivityRuleFacadeImplTest {

	@Resource
	private ActivityRuleFacade activityRuleFacadeImpl;
	
    @Before
    public void setUp() throws Exception {
    	
		RemoteServiceFactory.init();

        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
        activityRuleFacadeImpl = (ActivityRuleFacade) ctx.getBean("activityRuleFacadeImpl");
//        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    }


    /**
     * 测试修改规则
     * @throws Exception
     */
    @Test
    public void testUpdateEvent() throws Exception {
    	ActivityRuleDTO ruleDto = new ActivityRuleDTO();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = sdf.parse("2016-6-14 00:00:00");
    	ruleDto.setId(Long.valueOf(61));
    	ruleDto.setRuleCode("LY_TEST01");
    	ruleDto.setRuleName("测试修改03");
    	ruleDto.setInvalidTime(date);//失效时间
    	ruleDto.setCreator("ying.liu02");
    	ruleDto.setCreateTime(new Date());
    	String events = "1,2";
    	activityRuleFacadeImpl.updateRuleById(ruleDto, events);
    }
}
