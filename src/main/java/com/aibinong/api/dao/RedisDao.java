package com.aibinong.api.dao;

import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * RedisDao based on Jedis-2.7.3
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年6月30日 下午3:09:40
 */
@IocBean
public class RedisDao {
	private final static Logger LOG = LoggerFactory.getLogger(RedisDao.class);

	private JedisPool pool;

	private final static int DEFAULT_MAX_IDLE = 8;
	private final static int DEFAULT_MAX_TOTAL = 8;
	private final static int DEFAULT_TIMEOUT = 3000;
	private final static boolean DEFAULT_TEST_ON_BORROW = false;
	private final static boolean DEFAULT_TEST_ON_RETURN = false;
	
	private String host;
	private int port;
	private String password;
	private int timeout = DEFAULT_TIMEOUT;
	private int maxIdel = DEFAULT_MAX_IDLE;
	private int maxTotal = DEFAULT_MAX_TOTAL;
	private boolean testOnBorrow = DEFAULT_TEST_ON_BORROW;
	private boolean testOnReturn = DEFAULT_TEST_ON_RETURN;

	public void init() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			//最大空闲连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
			config.setMaxIdle(maxIdel);
			//最大连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
			config.setMaxTotal(maxTotal);
			config.setTestOnBorrow(testOnBorrow);
			config.setTestOnReturn(testOnReturn);
			if (StringUtils.isNotBlank(password)) {
				pool = new JedisPool(config, host, port, timeout, password);
			} else {
				pool = new JedisPool(config, host, port, timeout);
			}
			LOG.info("[{}:{}] redis pool init success!", new Object[] { this.host, this.port });
		} catch (Exception e) {
			LOG.error("redis poll init error", e);
			throw e;
		}
	}
	
	public void close() {
		if(pool != null) {
			pool.destroy();
		}
	}

	/**
	 * 获取一个实例
	 * @author zhang_zg
	 * @return
	 */
	public Jedis getJedis() {
		return pool.getResource();
	}

	/**
	 * 关闭实例
	 * @author zhang_zg
	 * @param jedis
	 */
	public void closeJedis(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	public String get(String key) {
		Jedis jedis = getJedis();
		try {
			return jedis.get(key);
		} finally {
			closeJedis(jedis);
		}
	}

	public void setex(String key, String value, int seconds) {
		if (value == null) {
			return;
		}

		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
			jedis.expire(key, seconds);
		} finally {
			closeJedis(jedis);
		}
	}

	public void expire(String key, int seconds) {
		if (seconds <= 0) {
			return;
		}
		Jedis jedis = getJedis();
		try {
			jedis.expire(key, seconds);
		} finally {
			closeJedis(jedis);
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxIdel() {
		return maxIdel;
	}

	public void setMaxIdel(int maxIdel) {
		this.maxIdel = maxIdel;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}
}