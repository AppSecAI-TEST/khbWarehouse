package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @Description 
 * @author zhenping.zhou
 * @CreateTime 2015年12月7日 上午10:42:15
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {  
        "classpath:spring-appContext.xml"  
}) 
public class ActivityCouponFacadeImplTest {

	/*@Resource
	private OrderDaoImpl orderMapper;*/
	
    /*@Before
    public void setUp() throws Exception {
    	
		RemoteServiceFactory.init();

//        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
        //activityCouponFacadeImpl = (ActivityCouponFacade) ctx.getBean("activityCouponFacadeImpl");
//        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    }*/

   /* *//**
     * 测试保存优惠券信息
     * @throws Exception
     *//*
//    @Test
    public void testRepalOrder() throws Exception {
    	activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    	
//    	activityCouponFacadeImpl.addActivityCoupon(activityCouponDto);
    }

    *//**
     * 测试冻结用户优惠券
     * @throws Exception
     *//*
//    @Test
    public void testFrozenUserCoupon() throws Exception {
    	Long id = 61L;
//    	String memberNo = "11";
//    	Long tradeId = 1L;
//    	BizTypeEnum bizType = BizTypeEnum.LM_LICAI;
    	ActivityCouponDTO couponDto = activityCouponFacadeImpl.selectCouponById(id);
    	
    	System.out.println(couponDto);
    }*/
}
