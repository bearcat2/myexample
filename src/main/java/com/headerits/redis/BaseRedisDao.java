package com.headerits.redis;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: 共用的redis数据访问对象 </p>
 * <p>Title: BaseRedisDao </p>
 * <p>Create Time: 2018/9/28 19:28 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public abstract class BaseRedisDao<T> implements RedisDao<T> {

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    @Autowired
    protected StringRedisTemplate stringRedisTemplate;

    /**操作失败*/
    protected static final int OPERATE_FAIL = -1;

    @Override
    public boolean expire(String key, long expireTime) {
        return StringUtils.isNotBlank(key) && expireTime > 0 ? this.redisTemplate.expire(key, expireTime, TimeUnit.SECONDS) : false;
    }

    @Override
    public long getExpire(String key) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.getExpire(key, TimeUnit.SECONDS) : OPERATE_FAIL;
    }

    @Override
    public boolean hasKey(String key) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.hasKey(key) : false;
    }

    @Override
    public void del(String... key) {
        if (ArrayUtils.isNotEmpty(key)) {
            this.redisTemplate.delete(Arrays.asList(key));
        }
    }

    @Override
    public abstract T get(String key);

    @Override
    public abstract boolean set(String key, T value);

    @Override
    public abstract boolean set(String key, T value, long expireTime);

    @Override
    public long incr(String key, long delta) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.opsForValue().increment(key, delta) : OPERATE_FAIL;
    }

    @Override
    public long decr(String key, long delta) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.opsForValue().increment(key, -delta) : OPERATE_FAIL;
    }

    @Override
    public Object hget(String key, String item) {
        return StringUtils.isNoneBlank(key, item) ? this.redisTemplate.opsForHash().get(key, item) : null;
    }

    @Override
    public Map<Object, Object> hmget(String key) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.opsForHash().entries(key) : null;
    }

    @Override
    public abstract boolean hmset(String key, Map<String, T> map);

    @Override
    public abstract boolean hmset(String key, Map<String, T> map, long expireTime);

    @Override
    public abstract boolean hset(String key, String item, T value);

    @Override
    public abstract boolean hset(String key, String item, T value, long expireTime);

    @Override
    public abstract void hdel(String key, T... item);

    @Override
    public boolean hHasKey(String key, String item) {
        return StringUtils.isNoneBlank(key, item) ? this.redisTemplate.opsForHash().hasKey(key, item) : false;
    }

    @Override
    public double hincr(String key, String item, double by) {
        return StringUtils.isNoneBlank(key, item) ? this.redisTemplate.opsForHash().increment(key, item, by) : OPERATE_FAIL;
    }

    @Override
    public double hdecr(String key, String item, double by) {
        return StringUtils.isNoneBlank(key, item) ? this.redisTemplate.opsForHash().increment(key, item, -by) : OPERATE_FAIL;
    }

    @Override
    public abstract Set<T> sGet(String key);

    @Override
    public boolean sHasKey(String key, String value) {
        return StringUtils.isNoneBlank(key, value) ? this.redisTemplate.opsForSet().isMember(key, value) : false;
    }

    @Override
    public abstract long sSet(String key, T... values);

    @Override
    public abstract long sSetAndTime(String key, long expireTime, T... values);

    @Override
    public long sGetSetSize(String key) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.opsForSet().size(key) : OPERATE_FAIL;
    }

    @Override
    public abstract long setRemove(String key, T... values);

    @Override
    public abstract List<T> lGet(String key, long start, long end);

    @Override
    public long lGetListSize(String key) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.opsForList().size(key) : OPERATE_FAIL;
    }

    @Override
    public abstract T lGetIndtex(String key, long index);

    @Override
    public abstract boolean lSet(String key, T value);

    @Override
    public abstract boolean lSet(String key, T value, long expireTime);

    @Override
    public abstract boolean lSet(String key, List<T> value);

    @Override
    public abstract boolean lSet(String key, List<T> value, long expireTime);

    @Override
    public abstract boolean lUpdateIndex(String key, long index, T value);

    @Override
    public abstract long lRemove(String key, long count, T value);

    @Override
    public abstract Set<T> zrangeByScore(String key, double value, double value1);
}
