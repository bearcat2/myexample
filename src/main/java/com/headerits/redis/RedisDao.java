package com.headerits.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: redis数据访问对象接口 </p>
 * <p>Title: RedisDao </p>
 * <p>Create Time: 2018/9/28 17:43 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public interface RedisDao<T> {

    /**
     * 设置键的过期时间
     *
     * @param key        键
     * @param expireTime 过期时间 单位秒
     * @return
     */
    boolean expire(String key, long expireTime);

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    long getExpire(String key);

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    boolean hasKey(String key);

    /**
     * 删除key
     *
     * @param key 可以传一个值 或多个
     */
    void del(String... key);

    /**
     * 获取字符串
     *
     * @param key 键
     * @return 值
     */
    T get(String key);

    /**
     * 插入字符串
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    boolean set(String key, T value);

    /**
     * 插入字符串并设置时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    boolean set(String key, T value, long expireTime);

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几
     * @return
     */
    long incr(String key, long delta);

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几
     * @return
     */
    long decr(String key, long delta);

    /**
     * 获取hash类型值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    Object hget(String key, String item);

    /**
     * 获取hash对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    Map<Object, Object> hmget(String key);

    /**
     * 设置多个hash值
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    boolean hmset(String key, Map<String, T> map);

    /**
     * 设置多个hash值并设置过期时间
     *
     * @param key        键
     * @param map        对应多个键值
     * @param expireTime 过期时间(秒)
     * @return true成功 false失败
     */
    boolean hmset(String key, Map<String, T> map, long expireTime);

    /**
     * 设置hash值,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    boolean hset(String key, String item, T value);

    /**
     * 设置hash值,如果不存在将创建并设置过期时间
     *
     * @param key        键
     * @param item       项
     * @param value      值
     * @param expireTime 过期时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    boolean hset(String key, String item, T value, long expireTime);

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    void hdel(String key, T... item);

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    boolean hHasKey(String key, String item);

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几
     * @return
     */
    double hincr(String key, String item, double by);

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少几
     * @return
     */
    double hdecr(String key, String item, double by);

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    Set<T> sGet(String key);

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    boolean sHasKey(String key, String value);

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    long sSet(String key, T... values);

    /**
     * 将set数据放入缓存
     *
     * @param key        键
     * @param expireTime 过期时间(秒)
     * @param values     值 可以是多个
     * @return 成功个数
     */
    long sSetAndTime(String key, long expireTime, T... values);

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    long sGetSetSize(String key);

    /**
     * 移除值为value的set
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    long setRemove(String key, T... values);

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    List<T> lGet(String key, long start, long end);

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    long lGetListSize(String key);

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    T lGetIndtex(String key, long index);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    boolean lSet(String key, T value);

    /**
     * 将list放入缓存
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间(秒)
     * @return
     */
    boolean lSet(String key, T value, long expireTime);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    boolean lSet(String key, List<T> value);

    /**
     * 将list放入缓存
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间单位秒
     * @return
     */
    boolean lSet(String key, List<T> value, long expireTime);

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    boolean lUpdateIndex(String key, long index, T value);

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    long lRemove(String key, long count, T value);

    Set<T> zrangeByScore(String key, double value, double value1);
}
