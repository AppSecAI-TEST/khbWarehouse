package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinnet.entity.Book;
import com.xinnet.entity.Order;
import com.xinnet.entity.User;
import com.xinnet.lock.Lock;
import com.xinnet.lock.RedisLock;
import com.xinnet.multipledatasource.entity.DataSourceOne;
import com.xinnet.multipledatasource.entity.DataSourceTwo;
import com.xinnet.multipledatasource.entity.OneAndTwoVO;
import com.xinnet.multipledatasource.service.MultipleDataService;
import com.xinnet.queue.producer.MessageProducer;
import com.xinnet.reids.Redis;
import com.xinnet.service.IOrderService;
import com.xinnet.service.IUserService;
import com.xinnet.service.YeepayDefaultService;
import com.xinnet.task.service.impl.QueryStockServiceImpl;
import com.xinnet.utils.SerializeUtil;
import com.xinnet.utils.SpringContext;
import com.xinnet.yeepay.YeepayDefault;
import com.xinnet.yeepay.dao.YeepayDefaultDao;


/**
 * 测试用例
 * @author hongbin.kang
 * @date 2017年8月7日下午4:54:05
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
	IOrderService orderService;
	@Resource
	private YeepayDefaultService yeepayDefaultService;
	@Resource
	private YeepayDefaultDao yeepayDefaultDao;
	@Resource
	Redis redis;
	@Resource
	MultipleDataService multipleDataSource;
	
	
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
	
	/**
	 * 测试rabbitmq
	 * @author hongbin.kang
	 * @date 2017年8月7日 下午4:48:26
	 * @throws Exception
	 */
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
	
	/**
	 * 测试批量插入
	 * @author hongbin.kang
	 * @date 2017年8月7日 下午4:49:02
	 * @throws Exception
	 */
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
	
	/**
	 * 测试redis存储bean
	 * @author hongbin.kang
	 * @date 2017年8月7日 下午4:49:16
	 * @throws Exception
	 */
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
  
	/**
	 * 测试redis获取bean
	 * @author hongbin.kang
	 * @date 2017年8月7日 下午4:49:16
	 * @throws Exception
	 */
	@Test  
    public void testRedisGet() throws Exception {
		
		@SuppressWarnings("unchecked")
		List<User> uList = (List<User>) SerializeUtil.unserialize(redis.get("p".getBytes()));
		System.out.println(uList);
    }
	
	/**
	 * redis的分布式锁
	 * @author hongbin.kang
	 * @date 2017年8月7日 下午4:49:53
	 * @throws Exception
	 */
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
	
	/**
	 * 测试事物管理
	 * @author hongbin.kang
	 * @date 2017年8月7日 下午4:50:20
	 * @throws Exception
	 */
	@Test  
    public void testYeepayService() throws Exception {
		yeepayDefaultService.savetestshiwu();
	}
	
	/**
	 * 测试mysql的读写分离
	 * @author hongbin.kang
	 * @date 2017年8月7日 下午4:50:33
	 * @throws Exception
	 */
	@Test
	public void testMysqlReAndWri() throws Exception {
		User user = new User();
		user.setUserName("name-");
		user.setPassWord("pass-");
		user.setEmail("email-");
		userService.add(user);
		
		System.out.println(userService.getUserById(2));
	}
	
	/**
	 * 测试mybatis的一、二级缓存
	 * @author hongbin.kang
	 * @date 2017年8月7日 下午4:50:53
	 * @throws Exception
	 */
	@Test
	public void testMybatisCache() throws Exception {
		Date first = new Date();
		yeepayDefaultService.selectMybatisCache(5);
		System.out.println(new Date().getTime() - first.getTime());
		YeepayDefault y = new YeepayDefault();
		y.setId(2L);
		y.setName("kang");
		yeepayDefaultService.update(y);
		Date secend = new Date();
		yeepayDefaultService.getAll();
		System.out.println(new Date().getTime() - secend.getTime());
		
		Date thirt = new Date();
		Order l1 = orderService.selectMybatisCache(4);
		System.out.println(new Date().getTime() - thirt.getTime());
		Date forth = new Date();
		Order l2 = orderService.selectMybatisSecondCache(4);
		System.out.println(new Date().getTime() - forth.getTime());
	}
	
	/**
	 * 测试多数据源
	 * @author hongbin.kang
	 * @date 2017年8月7日 下午4:56:45
	 */
	@Test
	public void testMultipleSource() {
		DataSourceOne one = new DataSourceOne("name-1","city-1");
		DataSourceTwo two = new DataSourceTwo("name-2","city-2");
		multipleDataSource.inster(one, two);
		
		OneAndTwoVO vo = multipleDataSource.queryData();
		System.out.println(vo);
	}
	
}
