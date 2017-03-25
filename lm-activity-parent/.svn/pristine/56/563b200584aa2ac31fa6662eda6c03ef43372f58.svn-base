package com.yeepay.g3.hessian.activity.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.yeepay.g3.core.activity.dao.ActivityUsercouponDao;
import com.yeepay.g3.core.activity.entity.ActivityCoupon;
import com.yeepay.g3.core.activity.entity.ActivityUsercoupon;
import com.yeepay.g3.facade.activity.dto.ActivityCouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityGrantRecordDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUsercouponDTO;
import com.yeepay.g3.facade.activity.enums.CouponStatusEnum;
import com.yeepay.g3.facade.activity.enums.GrantStatusEnum;
import com.yeepay.g3.facade.activity.enums.UsercouponStatusEnum;
//import com.yeepay.g3.facade.accounting.facade.AccountingFacade;
import com.yeepay.g3.facade.activity.facade.ActivityCouponFacade;
import com.yeepay.g3.facade.activity.facade.ActivityGrantRecordFacade;
import com.yeepay.g3.facade.activity.facade.ActivityUsercouponFacade;
import com.yeepay.g3.hessian.activity.util.AccountingBaseTest;
import com.yeepay.g3.hessian.activity.util.ApplicationContextUtil;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * @Description 
 * @author ying.liu
 * @CreateTime 2016-3-31
 * @version 1.0
 */
public class ActivityUsercouponFacadeImplTest extends AccountingBaseTest {

//	@Autowired
	private ActivityGrantRecordFacade activityGrantRecordFacadeImpl;
	private ActivityUsercouponDao activityUsercouponDaoImpl;
	@Resource
	private ActivityUsercouponFacade activityUsercouponFacadeImpl;
	
    @Before
    public void setUp() throws Exception {
    	
		RemoteServiceFactory.init();

        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
        activityUsercouponDaoImpl = (ActivityUsercouponDao) ctx.getBean("activityUsercouponDaoImpl");
      //  activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    }

    
    /**
     * 测试批量添加用户优惠券信息
     * @throws Exception
     */
//    @Test
    public void testBatchAdd() throws Exception{
    	List<ActivityUsercoupon> list = new ArrayList<ActivityUsercoupon>();
    	ActivityCoupon coupon = null;
    	for(int i=0;i<2;i++){
    		ActivityUsercoupon activityUsercouponDto = new ActivityUsercoupon();
    		coupon = new ActivityCoupon();
			coupon.setId(Long.valueOf(14));
        	activityUsercouponDto.setCoupon(coupon);
        	activityUsercouponDto.setCouponCount(2);
    		activityUsercouponDto.setCouponUsedCount(0);
    		activityUsercouponDto.setMemberNo("100001");
    		activityUsercouponDto.setValidityTimeEnd(new Date());
    		//TODO       状态待定义
    		activityUsercouponDto.setStatus(UsercouponStatusEnum.EFFECTIVE);
//    		list.add(activityUsercouponDto);
    		activityUsercouponDaoImpl.add("insert",activityUsercouponDto);
    	}
    //	List<ActivityUsercoupon> list1 = new ArrayList<ActivityUsercoupon>(); 
//    	activityUsercouponDaoImpl.batchInsert("addBatchGrantRecord",list);
    }

    /**
     * 测试查询用户优惠券
     * @throws Exception
     */
    @Test
    public void testSelectUserCoupon() throws Exception {
    	Long id = 61L;
//    	String memberNo = "11";
//    	Long tradeId = 1L;
//    	BizTypeEnum bizType = BizTypeEnum.LM_LICAI;
    	ActivityUsercoupon couponDto = activityUsercouponDaoImpl.selectByPrimaryKey(id);
    	
    	System.out.println(couponDto);
    }
    /**
     * 测试根据id查询用户优惠券信息
     * @throws Exception
     */
    @Test
    public void testSelectById() throws Exception{
    	activityUsercouponFacadeImpl = RemoteServiceFactory.getService(ActivityUsercouponFacade.class);
    	ActivityUsercouponDTO activityUsercouponDto = new ActivityUsercouponDTO();
    //	ActivityUsercoupon activityUsercoupon = new ActivityUsercoupon();
    	String id = "61";
    //	activityUsercoupon = activityUsercouponDaoImpl.selectByPrimaryKey(Long.valueOf(id));
    //	System.out.println(activityUsercoupon.getCoupon());
    	activityUsercouponDto = activityUsercouponFacadeImpl.selectUsercouponById(Long.valueOf(id));
    	System.out.println(activityUsercouponDto);
    	
    }
    
    
}
