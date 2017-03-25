package com.yeepay.g3.hessian.activity.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeepay.g3.facade.activity.dto.ActivityCouponDTO;
import com.yeepay.g3.facade.activity.facade.ActivityCouponFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * @Description 
 * @author zhenping.zhou
 * @CreateTime 2015年12月7日 上午10:42:15
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:activity-core-spring/applicationContext.xml"})  
public class ActivityCouponFacadeImplTest {

	@Resource
	private ActivityCouponFacade activityCouponFacadeImpl;
	
    @Before
    public void setUp() throws Exception {
    	
		RemoteServiceFactory.init();

//        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
        //activityCouponFacadeImpl = (ActivityCouponFacade) ctx.getBean("activityCouponFacadeImpl");
//        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    }

    /**
     * 测试保存优惠券信息
     * @throws Exception
     */
    @Test
    public void testSelectCoupon() throws Exception {
        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);

        Long id = 35L;
        ActivityCouponDTO coupon = activityCouponFacadeImpl.selectCouponById(id);
        System.out.println(coupon);
    }
    
    /**
     * 测试保存优惠券信息
     * @throws Exception
     */
//    @Test
    public void testRepalOrder() throws Exception {
    	activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    	
//    	activityCouponFacadeImpl.addActivityCoupon(activityCouponDto);
    }

    /**
     * 测试冻结用户优惠券
     * @throws Exception
     */
//    @Test
    public void testFrozenUserCoupon() throws Exception {
    	Long id = 61L;
//    	String memberNo = "11";
//    	Long tradeId = 1L;
//    	BizTypeEnum bizType = BizTypeEnum.LM_LICAI;
    	ActivityCouponDTO couponDto = activityCouponFacadeImpl.selectCouponById(id);
    	
    	System.out.println(couponDto);
    }
}
