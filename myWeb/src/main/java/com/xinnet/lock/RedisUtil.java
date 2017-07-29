package com.xinnet.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinnet.utils.PropertiesUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

  
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
    private static int MAX_ACTIVE = PropertiesUtils.getPropertyValueInt("/redis.properties", "jedis.maxActive");;
      
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = PropertiesUtils.getPropertyValueInt("/redis.properties", "jedis.maxIdle");;
      
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = PropertiesUtils.getPropertyValueInt("/redis.properties", "jedis.maxWait");;
  
    //超时时间
    private static int TIMEOUT = PropertiesUtils.getPropertyValueInt("/redis.properties", "jedis.timeout");;
      
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = PropertiesUtils.getPropertyValueBoolean("/redis.properties", "jedis.testOnBorrow");;
      
    private static JedisPool jedisPool = null;
      
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
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxActive(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWait(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
//            jedisPool = new JedisPool(config, ADDR_ARRAY.split(",")[0], PORT, TIMEOUT);
            jedisPool = new JedisPool(config, ADDR_ARRAY.split(",")[0], PORT, TIMEOUT,AUTH);
        } catch (Exception e) {
            logger.error("First create JedisPool error : "+e);
            try{
                //如果第一个IP异常，则访问第二个IP
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxActive(MAX_ACTIVE);
                config.setMaxIdle(MAX_IDLE);
                config.setMaxWait(MAX_WAIT);
                config.setTestOnBorrow(TEST_ON_BORROW);
                jedisPool = new JedisPool(config, ADDR_ARRAY.split(",")[1], PORT, TIMEOUT);
            }catch(Exception e2){
                logger.error("Second create JedisPool error : "+e2);
            }
        }
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
    public synchronized static Jedis getJedis() { 
        if (jedisPool == null) { 
            poolInit();
        }
        Jedis jedis = null;
        try { 
            if (jedisPool != null) { 
                jedis = jedisPool.getResource();
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
    	Jedis jedis = null;
    	Object obj;
    	try {
    		jedis = getJedis();
    		obj = rc.run(jedis);
    		if(jedis != null)
    			returnResource(jedis);
    		return obj;
		} catch (Exception e) {
			if(jedis != null)
				returnResource(jedis);
		}finally{
			if(jedis != null)
				returnResource(jedis);
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
     * 设置 String
     * @param key
     * @param value
     */
    /*public static void setString(String key ,String value){
        try {
            value = StringUtil.isEmpty(value) ? "" : value;
            getJedis().set(key,value);
        } catch (Exception e) {
            logger.error("Set key error : "+e);
        }
    }
      
    *//**
     * 设置 过期时间
     * @param key
     * @param seconds 以秒为单位
     * @param value
     *//*
    public static void setString(String key ,int seconds,String value){
        try {
            value = StringUtil.isEmpty(value) ? "" : value;
            getJedis().setex(key, seconds, value);
        } catch (Exception e) {
            logger.error("Set keyex error : "+e);
        }
    }*/
      
    /**
     * 获取String值
     * @param key
     * @return value
     */
    public static String getString(String key){
        if(getJedis() == null || !getJedis().exists(key)){
            return null;
        }
        return getJedis().get(key);
    }
      
}
