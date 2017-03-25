package com.yeepay.g3.hessian.activity.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.yeepay.g3.facade.activity.dto.ActivityCouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityGrantRecordDTO;
import com.yeepay.g3.facade.activity.enums.CouponStatusEnum;
import com.yeepay.g3.facade.activity.enums.GrantStatusEnum;
//import com.yeepay.g3.facade.accounting.facade.AccountingFacade;
import com.yeepay.g3.facade.activity.facade.ActivityCouponFacade;
import com.yeepay.g3.facade.activity.facade.ActivityGrantRecordFacade;
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
public class ActivityGrantRecordFacadeImplTest extends AccountingBaseTest {

//	@Autowired
	private ActivityGrantRecordFacade activityGrantRecordFacadeImpl;
	
    @Before
    public void setUp() throws Exception {
    	
		RemoteServiceFactory.init();

        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
        activityGrantRecordFacadeImpl = (ActivityGrantRecordFacade) ctx.getBean("activityGrantRecordFacadeImpl");
      //  activityCouponFacadeImpl = RemoteServiceFactory.getService(ActivityCouponFacade.class);
    }

    /**
     * 测试保存批量发放记录
     * @throws Exception
     */
    @Test
    public void testRepalOrder() throws Exception {
    	ActivityGrantRecordDTO activityGrantRecordDto = new ActivityGrantRecordDTO();
    	activityGrantRecordDto.setBatchId("1");
    	activityGrantRecordDto.setBatchGrantName("test1");
    	activityGrantRecordDto.setCouponId(Long.parseLong("1"));
    	activityGrantRecordDto.setCouponName("test");
    	activityGrantRecordDto.setGrantStatus(GrantStatusEnum.CHECKING);
    	activityGrantRecordDto.setMemberCount(2);
    	String str = "11110;11111";
    	byte[] memberList = str.getBytes("UTF-8");
    	/*for(int i = 0;i < memberList.length;i++){
    		System.out.println(memberList[i]);
    	}
    	String res = new String(memberList,"UTF-8");
    	System.out.println(res);*/
    	activityGrantRecordDto.setMemberNoList(memberList);
    	activityGrantRecordDto.setPerGrantCount(10);
    	activityGrantRecordDto.setCreator("ying.liu");
    	
    	activityGrantRecordFacadeImpl.addGrantRecord(activityGrantRecordDto);
    	List<ActivityGrantRecordDTO> list = activityGrantRecordFacadeImpl.selectListByParams(activityGrantRecordDto);
    	String str1 = new String(list.get(0).getMemberNoList());
    	System.out.println(str1);
    	/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = sdf.parse("2016-3-28 00:00:00");
    	Date date1 = sdf.parse("2016-3-5 00:00:00");*/
  
    
    }
    /**
     * 测试审批修改批量发放记录
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception{
    	ActivityGrantRecordDTO activityGrantRecordDto = new ActivityGrantRecordDTO();
    	activityGrantRecordDto.setId(Long.valueOf("12"));
    	activityGrantRecordDto.setVersion(Long.valueOf("0"));
    	activityGrantRecordDto.setCheckedTime(new Date());
    	activityGrantRecordDto.setCheckor("ying.liu");
    	activityGrantRecordDto.setGrantStatus(GrantStatusEnum.RETURN_BACK);
    	activityGrantRecordFacadeImpl.checkGrantRecord(activityGrantRecordDto);
    }
    
    
}
