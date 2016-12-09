package org.spring.redis.core;

import java.util.Set;

/** <p>
 * 标题: 通用缓存底层接口
 * </p>
 * <p>
 * 描述:
 * </p>
 * <p>
 * 公司: www.chinaunicom.com
 * </p>
 * 
 * @autho liuce
 * @time 2016-5-15 下午7:18:04 */
public interface CacheManager {

	/** <p>
	 * 方法名称：根据key获取value值
	 * </p>
	 * <p>
	 * 方法说明：
	 * </p>
	 * 
	 * @param key
	 * @return
	 * @autho liuce
	 * @time 2016-5-15 下午7:30:22 */
	public byte[] get(byte[] key);

	/** <p>
	 * 方法名称：设置一个不过期的记录
	 * </p>
	 * <p>
	 * 方法说明：
	 * </p>
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @autho liuce
	 * @time 2016-5-15 下午7:31:08 */
	public byte[] set(byte[] key, byte[] value);

	/** <p>
	 * 方法名称：设置一个带失效时间的记录
	 * </p>
	 * <p>
	 * 方法说明：
	 * </p>
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 * @autho liuce
	 * @time 2016-5-15 下午7:32:01 */
	public byte[] set(byte[] key, byte[] value, int expire);

	/** <p>
	 * 方法名称：根据key删除记录
	 * </p>
	 * <p>
	 * 方法说明：
	 * </p>
	 * 
	 * @param key
	 * @autho liuce
	 * @time 2016-5-15 下午7:32:31 */
	public void del(byte[] key);

	/** <p>
	 * 方法名称：删除当前库中的所有元素
	 * </p>
	 * <p>
	 * 方法说明：
	 * </p>
	 * 
	 * @autho liuce
	 * @time 2016-5-15 下午7:49:10 */
	public void flushDB();

	/** <p>
	 * 方法名称：根据前缀查询对应的key
	 * </p>
	 * <p>
	 * 方法说明：
	 * </p>
	 * 
	 * @param pattern
	 * @return
	 * @autho liuce
	 * @time 2016-5-15 下午8:00:36 */
	public Set<byte[]> keys(String pattern);

	/** 获取tair中key的数量<br>
	 * 适用场景: <br>
	 * 调用方式: <br>
	 * 业务逻辑说明<br>
	 * 
	 * @return
	 * @autho liuce
	 * @time 2016-5-26 上午12:55:08 */
	public Long dbSize();

	/** 修改value的超时时间<br>
	 * 适用场景: <br>
	 * 调用方式: <br>
	 * 业务逻辑说明<br>
	 * 
	 * @autho liuce
	 * @time 2016年11月6日 上午2:23:00 */
	public void setValueExpireTime(byte[] key, int expire);

	/** 获取key的失效时间<br>
	 * 适用场景: <br>
	 * 调用方式: <br>
	 * 业务逻辑说明<br>
	 * 
	 * @param key
	 * @return
	 * @autho liuce
	 * @time 2016年11月9日 上午12:10:41 */
	public Long getExpireTimeByKey(byte[] key);
}
