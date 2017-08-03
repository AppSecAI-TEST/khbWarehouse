package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinnet.dao.OrderDao;
import com.xinnet.dao.impl.OrderDaoImpl;
import com.xinnet.entity.Book;
import com.xinnet.entity.Order;
import com.xinnet.entity.User;
import com.xinnet.lock.Lock;
import com.xinnet.lock.RedisLock;
import com.xinnet.queue.producer.MessageProducer;
import com.xinnet.reids.Redis;
import com.xinnet.service.IUserService;
import com.xinnet.task.service.impl.QueryStockServiceImpl;
import com.xinnet.utils.SerializeUtil;
import com.xinnet.utils.SpringContext;
import com.xinnet.yeepay.YeepayDefault;
import com.xinnet.yeepay.dao.YeepayDefaultDao;


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
public class RabbitMqTest {

	@Resource
	private MessageProducer messageProducer;
	@Resource
	QueryStockServiceImpl queryStockServiceImpl;
	@Resource
	IUserService userService;
	@Resource
	private YeepayDefaultDao yeepayDefaultDao;
	
	@Resource
	Redis redis;
	
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
	@Test  
    public void query() throws Exception {  
		IUserService u = (IUserService)SpringContext.getBeanByName("userService");
		System.out.println(u.getUserById(1));
		queryStockServiceImpl.queryStock();
    } 
	
	@Test  
    public void should_send_a_amq_message() throws Exception {  
        int a = 100;  
        while (a > 0) {  
        	Book book = new Book(a,"name--"+ a,"author--"+ a);
        	a = a - 1;
            messageProducer.sendMessage(book);  
            try {  
                //暂停一下，好让消息消费者去取消息打印出来  
                Thread.sleep(1000);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
  
        }  
    } 
	
//	@Test  
//    public void testBatchUser() throws Exception {  
//		List<User> uList = new ArrayList<>();
//		for(int i=0;i<3;i++) {
//			User user = new User();
//			user.setUserName("name-"+i);
//			user.setPassWord("pass-"+i);
//			user.setEmail("email-"+i);
//			uList.add(user);
//		}
//		userService.batchInsert(uList);
//    }
	
	@Test  
    public void testRedis() throws Exception {
		List<User> uList = new ArrayList<User>();
		for(int i=0;i<3;i++) {
			User user = new User();
			user.setUserName("name-"+i);
			user.setPassWord("pass-"+i);
			user.setEmail("email-"+i);
			uList.add(user);
		}
		redis.set("p".getBytes(), SerializeUtil.serialize(uList));
		System.out.println("p".getBytes());
    }
  
	@Test  
    public void testRedisGet() throws Exception {
		
		@SuppressWarnings("unchecked")
		List<User> uList = (List<User>) SerializeUtil.unserialize(redis.get("p".getBytes()));
		System.out.println(uList);
    }
	
	@Test  
    public void testTryLock() throws Exception {
		Lock lock = new RedisLock("kang",10);
		try {
			if (lock.tryLock(6)) {
				System.out.println("jinlaile");
			}

		} finally {
			lock.unlock();
		}
    }
	
	
	@Test  
    public void testYeepayDao() throws Exception {
		YeepayDefault yeepay = new YeepayDefault();
		yeepay.setAge(12);
		yeepay.setCity("china");
		yeepay.setName("kk");
		yeepayDefaultDao.add("insertSelective", yeepay);
		System.out.println(yeepayDefaultDao.query("selectByPrimaryKey", 1));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "kk");
		System.out.println(yeepayDefaultDao.query("selectByParam", map));
		yeepay.setId(2L);
		yeepay.setName("BB");
		yeepayDefaultDao.update(yeepay);
    }
	
	@Test
	public void testMysqlReAndWri() throws Exception {
		User user = new User();
		user.setUserName("name-");
		user.setPassWord("pass-");
		user.setEmail("email-");
		userService.add(user);
		
		System.out.println(userService.getUserById(2));
	}
	
	
	
}
