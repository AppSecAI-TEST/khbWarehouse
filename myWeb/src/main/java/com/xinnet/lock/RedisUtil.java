package com.xinnet.lock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinnet.utils.PropertiesUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

@SuppressWarnings({ "unchecked", "rawtypes" })  
public class RedisUtil {
    
	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
    //Redis服务器IP
    private static String ADDR_ARRAY = PropertiesUtils.getPropertyValue("/redis.properties", "jedis.host");
      
    //Redis的端口号
    private static int PORT = PropertiesUtils.getPropertyValueInt("/redis.properties", "jedis.port");
      
    //访问密码
    private static String AUTH = PropertiesUtils.getPropertyValue("/redis.properties", "jedis.pass");
      
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = PropertiesUtils.getPropertyValueInt("/redis.properties", "jedis.maxActive");
      
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = PropertiesUtils.getPropertyValueInt("/redis.properties", "jedis.maxIdle");
      
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = PropertiesUtils.getPropertyValueInt("/redis.properties", "jedis.maxWait");
  
    //超时时间
    private static int TIMEOUT = PropertiesUtils.getPropertyValueInt("/redis.properties", "jedis.timeout");
      
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = PropertiesUtils.getPropertyValueBoolean("/redis.properties", "jedis.testOnBorrow");
      
    //哪种模式的redis，哨兵，普通
    private static String MODE = PropertiesUtils.getPropertyValue("/redis.properties", "jedis.mode");
    
  //哪种模式的redis，哨兵，普通
    private static String masterName = PropertiesUtils.getPropertyValue("/redis.properties", "jedis.masterName");
    
    
	private static Pool jedisPool = null;
      
    /**
     * redis过期时间,以秒为单位
     */
    public final static int EXRP_HOUR = 60*60;          //一小时
    public final static int EXRP_DAY = 60*60*24;        //一天
    public final static int EXRP_MONTH = 60*60*24*30;   //一个月
      
    /**
     * 初始化Redis连接池
     */
    private static void initialPool(){
    	
    	if ("sentinel".equals(MODE)) {
    		logger.info("init SentinelsPool redis");
    		initSentinelsPool();
    		logger.info("init SentinelsPool redis success");
    	} else {
    		logger.info("init JedisPool redis");
    		initJedisPool();
    		logger.info("init JedisPool redis success");
    	}
    }
    
    /**
     * 初始化哨兵
     * @author hongbin.kang
     * @date 2017年7月31日 下午11:57:09
     * @param prop
     */
	private static void initSentinelsPool() {
		JedisPoolConfig config = initPoolConfig();
		Set sentinels = new HashSet(Arrays.asList(getHostAndPorts()));
		jedisPool = new JedisSentinelPool(masterName,
				sentinels, config, TIMEOUT);
	}
    /**
     * 单个的
     * @author hongbin.kang
     * @date 2017年8月1日 上午12:16:21
     */
    private static void initJedisPool(){
    	JedisPoolConfig config = initPoolConfig();
//        jedisPool = new JedisPool(config, ADDR_ARRAY.split(",")[0], PORT, TIMEOUT);
        jedisPool = new JedisPool(config, ADDR_ARRAY, PORT, TIMEOUT,AUTH);
    }
    
    /**
     * 初始化poolConfig
     * @author hongbin.kang
     * @date 2017年8月1日 上午12:15:34
     * @return
     */
    private static JedisPoolConfig initPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
		return config;
	}
    
    private static String[] getHostAndPorts() {
		return PropertiesUtils.getPropertyValue("/redis.properties", "jedis.sentinels").trim().split(",");
	}
      
      
    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (jedisPool == null) { 
            initialPool();
        }
    }
  
      
    /**
     * 同步获取Jedis实例
     * @return Jedis
     */
    public synchronized static Jedis getResource() { 
    	logger.info("start get redis source");
        if (jedisPool == null) { 
            poolInit();
        }
        Jedis jedis = null;
        try { 
            if (jedisPool != null) { 
                jedis = (Jedis) jedisPool.getResource();
                logger.info("success get redis source");
            }
        } catch (Exception e) { 
            logger.error("Get jedis error : "+e);
        }
        return jedis;
    } 
    
    /**
     * 使用redis操作获取回调
     * @author hongbin.kang
     * @date 2017年7月29日 下午8:36:53
     * @param rc
     * @return
     */
    public static Object call(RedisCall rc) {
    	logger.info("execute redis call start");
    	Jedis jedis = null;
    	Object obj;
    	try {
    		jedis = getResource();
    		obj = rc.run(jedis);
    		logger.info("execute redis call result={}",obj);
    		if(jedis != null){
    			closeResource(jedis);
    		}
    		logger.info("execute redis call success");
    		return obj;
		} catch (Exception e) {
			if(jedis != null) {
				closeResource(jedis);
			}
		}
       return null;
    }
    
      
      
    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool !=null) {
            jedisPool.returnResource(jedis);
        }
    }
    
    /**
     * 释放jedis资源
     * @author hongbin.kang
     * @date 2017年7月29日 下午8:42:07
     * @param jedis
     */
    public static void closeResource(Jedis jedis) {
    	logger.info("start close redis source");
    	if (jedis != null){
			if (jedis.isConnected()){
				jedis.close();
			} 
		}
		logger.info("success close redis source");
	}
}
