import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeepay.g3.core.activity.dao.ActivityMemberOperecordDao;
import com.yeepay.g3.core.activity.entity.ActivityMemberOperecord;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * @author 陈大涛
 * 2016-7-1下午3:37:53
 */

/**
 * @author 陈大涛
 * 2016-7-1下午3:37:53
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:activity-core-spring/applicationContext.xml"})  
public class ActivityMemberOperecordTest {
	@Resource
	private ActivityMemberOperecordDao activityMemberOperecordDaoImpl;
	  @Before
	    public void setUp() throws Exception {
	    	
//			RemoteServiceFactory.init();
//			 ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
//			 activityDrawPrizeFacadeImpl = (ActivityDrawPrizeFacadeImpl) ctx.getBean("activityDrawPrizeFacadeImpl");

//	        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
	        //activityCouponFacadeImpl = (ActivityCouponFacade) ctx.getBean("activityCouponFacadeImpl");
//	        activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
	    }
	  @Test
	  public void drawPrize() throws Exception {
		  String memberNo="811234567401";
		  Date startTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-05-01 15:46:11");
		  Date endTime=null;
		  String type="BIND_CARD";
		  List<ActivityMemberOperecord> resultList =activityMemberOperecordDaoImpl.queryActivityMemberOperecordList(memberNo, startTime, endTime, type);
		  System.out.println(resultList.size());
	  }
}
