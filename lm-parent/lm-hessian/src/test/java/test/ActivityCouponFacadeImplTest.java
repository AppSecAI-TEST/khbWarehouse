package test;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinnet.facade.dto.UserInfoDTO;
import com.xinnet.facade.facade.UserInfoFacade;


/**
 * 
 * @author hongbin.kang
 * @date 2017年3月25日下午10:56:03
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {  
        "classpath*:spring-appContext.xml"  
}) 
public class ActivityCouponFacadeImplTest {

	@Resource
	private UserInfoFacade userInfoFacadeImpl;
	
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
    @Test
    public void testfirst() throws Exception {
        UserInfoDTO dto = userInfoFacadeImpl.selectUserInfoById("1");
        System.out.println(dto);
    }
    
  
}
