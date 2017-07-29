package test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinnet.entity.Book;
import com.xinnet.entity.User;
import com.xinnet.lock.Lock;
import com.xinnet.lock.RedisLock;
import com.xinnet.queue.producer.MessageProducer;
import com.xinnet.reids.Redis;
import com.xinnet.service.IUserService;
import com.xinnet.task.service.impl.QueryStockServiceImpl;
import com.xinnet.utils.SerializeUtil;


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
		queryStockServiceImpl.queryStock();
    } 
	
	/*@Test  
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
    } */
	
	@Test  
    public void testBatchUser() throws Exception {  
		List<User> uList = new ArrayList<>();
		for(int i=0;i<3;i++) {
			User user = new User();
			user.setUserName("name-"+i);
			user.setPassWord("pass-"+i);
			user.setEmail("email-"+i);
			uList.add(user);
		}
		userService.batchInsert(uList);
    }
	
	@Test  
    public void testRedis() throws Exception {
		List<User> uList = new ArrayList<>();
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
		Lock lock = new RedisLock("kang",20);
		try {
			if (lock.tryLock(16)) {
				System.out.println("jinlaile");
			}

		} finally {
			lock.unlock();
		}
    }
	
}
