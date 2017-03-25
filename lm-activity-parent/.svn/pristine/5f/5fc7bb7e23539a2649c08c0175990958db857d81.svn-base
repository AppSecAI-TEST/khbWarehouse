package com.yeepay.g3.hessian.activity.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeepay.g3.core.activity.dao.ActivityUsercouponDao;
import com.yeepay.g3.facade.activity.dto.ActivityUsercouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUsercouponRecordDTO;
import com.yeepay.g3.facade.activity.enums.BizTypeEnum;
import com.yeepay.g3.facade.activity.facade.ActivityCouponTradeFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * @Description 
 * @author zhenping.zhou
 * @CreateTime 2015年12月7日 上午10:42:15
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:activity-core-spring/applicationContext.xml"})  
public class ActivityCouponTradeFacadeImplTest {

	@Resource
	private ActivityCouponTradeFacade activityCouponTradeFacadeImpl;
	
	@Resource
	private ActivityUsercouponDao activityUsercouponDaoImpl;
	
    @Before
    public void setUp() throws Exception {
    	
		RemoteServiceFactory.init();

//        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
        //activityCouponFacadeImpl = (ActivityCouponFacade) ctx.getBean("activityCouponFacadeImpl");
//        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    }

    /**
     * 测试查询用户可使用优惠券
     * @throws Exception
     */
//    @Test
    public void testSelectTradeUsercouponList() throws Exception {
    	String memberNo = "811234567110";
    	String channelCode = "FIXED";
    	String bigColumnCode = "MONY";
    	String smallColumnCode = "45D";
    	List<ActivityUsercouponDTO> list = activityCouponTradeFacadeImpl.
    			selectTradeUsercouponList(memberNo, channelCode, bigColumnCode, smallColumnCode);
    	
    	System.out.println(list.size());
    }
    
    /**
     * 测试冻结用户优惠券
     * @throws Exception
     */
//    @Test
    public void testFrozenUserCoupon() throws Exception {
    	Long usercouponId = 120L;
    	String memberNo = "811234567930";
    	Long tradeId = 123L;
    	BizTypeEnum bizType = BizTypeEnum.LM_LICAI;
    	activityCouponTradeFacadeImpl.frozenUserCoupon(usercouponId, memberNo, tradeId, bizType);
    }
    
    /**
     * 测试解冻用户优惠券
     * @throws Exception
     */
//    @Test
    public void testUnFrozenUserCoupon() throws Exception {
    	Long usercouponId = 120L;
    	String memberNo = "811234567930";
    	Long tradeId = 123L;
    	BizTypeEnum bizType = BizTypeEnum.LM_LICAI;
    	activityCouponTradeFacadeImpl.unFrozenUserCoupon(usercouponId, memberNo, tradeId, bizType);
    }
    
    /**
     * 测试消费用户优惠券
     * @throws Exception
     */
//    @Test
    public void testConsumeUserCoupon() throws Exception {
    	Long usercouponId = 120L;
    	String memberNo = "811234567930";
    	Long tradeId = 123L;
    	BizTypeEnum bizType = BizTypeEnum.LM_LICAI;
    	activityCouponTradeFacadeImpl.consumeUserCoupon(usercouponId, memberNo, tradeId, bizType);
    }
    
    /**
     * 测试用户优惠券列表,包含未使用，已消费，已过期
     */
    @Test
    public void selectUserCouponList() {
    	String memberNo = "811234567930";
    	//已使用用户优惠券列表
    	List<ActivityUsercouponRecordDTO> usercouponRecordList = activityCouponTradeFacadeImpl.selectUserCouponUsedList(memberNo, BizTypeEnum.LM_LICAI);
    	
    	//未使用用户优惠券列表
    	List<ActivityUsercouponDTO> usercouponUnuseList = activityCouponTradeFacadeImpl.selectUserCouponUnuseList(memberNo);
    	
    	//已过期用户优惠券列表
    	List<ActivityUsercouponDTO> usercouponExpiredList = activityCouponTradeFacadeImpl.selectUserCouponExpiredList(memberNo);

    	System.out.println(usercouponExpiredList.size());
    }
    
    /**
     * 测试用户领取优惠券
     */
//    @Test
    public void testReceiveUserCoupon() {
    	String eventCode = "TEST_007";
    	String memberNo = "811234567930";
    	List<ActivityUsercouponDTO> usercouponList = activityCouponTradeFacadeImpl.receiveUserCoupon(eventCode, memberNo);
    	
    	System.out.println(usercouponList.size());
    	
    }
}
