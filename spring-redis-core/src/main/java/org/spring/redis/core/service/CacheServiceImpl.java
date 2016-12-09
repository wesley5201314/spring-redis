package org.spring.redis.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.redis.core.CacheManager;
import org.spring.redis.core.util.SerializeUtils;

/** 通用缓存实现<br>*/
public class CacheServiceImpl implements CacheService {

	private final static Logger log = LoggerFactory.getLogger(CacheServiceImpl.class);

	/** 缓存底层接口 */
	private CacheManager        cacheManager;

	/** 返回 缓存底层接口
	 * 
	 * @return 缓存底层接口 */
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	/** 设置 缓存底层接口
	 * 
	 * @param cacheManager
	 *        缓存底层接口 */
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void put(String key, Object value) {

		cacheManager.set(key.getBytes(), SerializeUtils.serialize(value));

	}

	@Override
	public void put(String key, Object value, int expire) {
		cacheManager.set(key.getBytes(), SerializeUtils.serialize(value), expire);
	}

	@Override
	public Object get(String key) {

		byte[] value = cacheManager.get(key.getBytes());

		if (value == null) {
			return null;
		}
		return SerializeUtils.deserialize(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key, Class<T> requiredType) {
		byte[] value = cacheManager.get(key.getBytes());

		if (value == null) {
			return null;
		}
		return (T) SerializeUtils.deserialize(value);
	}

	@Override
	public void delete(String key) {
		cacheManager.del(key.getBytes());
	}

	@Override
	public Set<String> getkeys(String pattern) {

		Set<String> keys = new HashSet<String>();

		Set<byte[]> keybs = cacheManager.keys(pattern);

		for (byte[] bs : keybs) {
			keys.add(new String(bs));
		}

		return keys;
	}

	@Override
	public Long getExpireTimeByKey(String key) {
		return cacheManager.getExpireTimeByKey(key.getBytes());
	}

}
