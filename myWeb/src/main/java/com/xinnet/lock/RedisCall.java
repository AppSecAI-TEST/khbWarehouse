package com.xinnet.lock;

import redis.clients.jedis.Jedis;

public interface RedisCall {

	public abstract Object run(Jedis jedis);
}

