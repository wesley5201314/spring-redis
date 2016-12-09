package org.spring.redis.core.redis;

import java.util.Set;

import org.spring.redis.core.CacheManager;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** redis管理类 支持注入连接池 封装基础的增删改查功能
 * 
 * @author liuce
 * @time 2016年4月13日13:57:10 */
public class RedisManager implements CacheManager {

	/** 主机名称或者ip地址 */
	private String           host      = "127.0.0.1";

	/** 端口 */
	private int              port      = 6379;

	/** 失效时间 0为永不失效 */
	private int              expire    = 0;

	/** 连接超时时间 会自动重连 */
	private int              timeout   = 0;

	/** 密码 */
	private String           password  = "";

	/** 连接池 */
	private JedisPool jedisPool = null;

	public RedisManager() {

	}

	/** 初始化方法 */
	public void init() {
		if (jedisPool == null) {
			if (password != null && !"".equals(password)) {
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password);
			}
			else if (timeout != 0) {
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout);
			}
			else {
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
			}

		}
	}

	/** get value from redis
	 * 
	 * @param key
	 * @return */
	@Override
	public byte[] get(byte[] key) {
		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.get(key);
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	/** set
	 * 
	 * @param key
	 * @param value
	 * @return */
	@Override
	public byte[] set(byte[] key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
			if (this.expire != 0) {
				jedis.expire(key, this.expire);
			}
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	/** set
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return */
	@Override
	public byte[] set(byte[] key, byte[] value, int expire) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	/** del
	 * 
	 * @param key */
	@Override
	public void del(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/** flush */
	@Override
	public void flushDB() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.flushDB();
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/** size */
	@Override
	public Long dbSize() {
		Long dbSize = 0L;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			dbSize = jedis.dbSize();
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return dbSize;
	}

	/** keys
	 * 
	 * @param regex
	 * @return */
	@Override
	public Set<byte[]> keys(String pattern) {
		Set<byte[]> keys = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			keys = jedis.keys(pattern.getBytes());
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return keys;
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

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public void setValueExpireTime(byte[] key, int expire) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public Long getExpireTimeByKey(byte[] key) {
		Long expireTime = -1L;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			expireTime = jedis.ttl(key);
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return expireTime;
	}

}
