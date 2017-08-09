package com.xinnet.lock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;


public class RedisLock implements Lock {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);
	
	public static final int DEFAULT_EXPIRE_TIME = 10000;
	public static final long DEFAULT_TRY_INTERVAL = 50L;
	public static final String SET_SUCCESS = "OK";
	private int expire;
	private String key;
	private String val;
	private long tryInterval = DEFAULT_TRY_INTERVAL;
	
	public RedisLock() {
	
	}
	
	public RedisLock(String key) {
		this.key = key;
		val = UUID.randomUUID().toString();
		expire = 10000;
		tryInterval = 50L;
	}

	public RedisLock(String key, int expire) {
		this.key = key;
		val = UUID.randomUUID().toString();
		if (expire > 0)
			this.expire = expire * 1000;
		else
			this.expire = 10000;
	}

	public RedisLock(String key, int expire, long tryInterval) {
		this.key = key;
		val = UUID.randomUUID().toString();
		if (expire > 0)
			this.expire = expire * 1000;
		else
			this.expire = 10000;
		if (tryInterval > 0L)
			this.tryInterval = 50L;
		else
			this.tryInterval = 50L;
	}

	//判断是否存在
	private boolean setNx(final String key, final String val,
			final int expireTime) {
		
		return (Boolean)RedisUtil.call(new RedisCall() {
			
			@Override
			public Object run(Jedis jedis) {
				if (jedis.setnx(key, val).longValue() == 1L) {
					jedis.expire(key, expireTime / 1000);
					return Boolean.valueOf(true);
				} else {
					return Boolean.valueOf(false);
				}
			}
		});
	}
	
	@Override
	public boolean lock() {
		if (setNx(key, val, expire)) {
			return Boolean.valueOf(true);
		} else {
			return Boolean.valueOf(false);
		}
	}

	@Override
	public boolean tryLock(int timeout) {
		for (long tryTime = System.currentTimeMillis() + (long) timeout * 1000L; System
				.currentTimeMillis() < tryTime;) {
			if (setNx(key, val, expire)) {
				return true;
			}
			
			try {
				TimeUnit.MILLISECONDS.sleep(tryInterval);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		return false;
	}

	@Override
	public void unlock() {
		RedisUtil.call(new RedisCall() {
			
			@Override
			public Object run(Jedis jedis) {
				jedis.del(key);
				return true;
			}
		});
	}


}
