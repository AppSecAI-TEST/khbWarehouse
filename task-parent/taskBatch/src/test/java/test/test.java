package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 测试用例
 * @author hongbin.kang
 * @date 2017年8月7日下午4:54:05
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {  
        "classpath:spring-appContext.xml"  
}) 
public class test {

//	@Resource
//	MultipleDataService multipleDataSource;
	
	
	/*@Resource
	private OrderDaoImpl orderMapper;*/
	
    /*@Before
    public void setUp() throws Exception {
    	
		RemoteServiceFactory.init();

//        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
        //activityCouponFacadeImpl = (ActivityCouponFacade) ctx.getBean("activityCouponFacadeImpl");
//        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    }*/

    /**
     * 测试保存优惠券信息
     * @throws Exception
     */
	/*@Test  
    public void query() throws Exception {  
		IUserService u = (IUserService)SpringContext.getBeanByName("userService");
		System.out.println(u.getUserById(1));
		queryStockServiceImpl.queryStock();
    }*/ 
	
}
