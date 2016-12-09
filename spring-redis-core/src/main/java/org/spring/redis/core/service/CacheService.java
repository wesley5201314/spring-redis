package org.spring.redis.core.service;

import java.util.List;
import java.util.Set;

/** <br>
 * 标题: 通用缓存接口<br>
 * 描述: 提供基本的get、set、delete方法<br>
 * 公司: www.chinaunicom.com<br>
 * 
 * @autho liuce
 * @time 2016-7-18 下午1:06:30 */
public interface CacheService {

	/** 往缓存中存放值（永不过期）<br> */
	public void put(String key, Object value);

	/** 往缓存中存放值（一段时间内有效）<br> */
	public void put(String key, Object value, int expire);

	/** 从缓存中获取值<br>*/
	public Object get(String key);

	/** 从缓存中获取指定类型的结果<br>*/
	public <T> T get(String key, Class<T> requiredType);

	/** 从缓存中删除指定的key<br>*/
	public void delete(String key);

	/** 根据表达式获取前缀一样的Key集合<br>*/
	public Set<String> getkeys(String pattern);
	
	/** 获取key的失效时间<br>*/
	public Long getExpireTimeByKey(String key);

}
