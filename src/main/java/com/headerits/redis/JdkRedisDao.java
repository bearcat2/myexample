package com.headerits.redis;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: jdk类型redis数据访问对象</p>
 * <p>Title: StringRedisDao </p>
 * <p>Create Time: 2018/9/28 20:07 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Repository
public class JdkRedisDao extends BaseRedisDao<Object> {

    @Override
    public Object get(String key) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.opsForValue().get(key) : null;
    }

    @Override
    public boolean set(String key, Object value) {
        if (StringUtils.isNotBlank(key) && value != null) {
            this.redisTemplate.opsForValue().set(key, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean set(String key, Object value, long expireTime) {
        if (StringUtils.isNotBlank(key) && value != null) {
            this.redisTemplate.opsForValue().set(key, value, expireTime);
            return true;
        }
        return false;
    }

    @Override
    public boolean hmset(String key, Map<String, Object> map) {
        if (StringUtils.isNotBlank(key) && !CollectionUtils.isEmpty(map)) {
            this.redisTemplate.opsForHash().putAll(key, map);
            return true;
        }
        return false;
    }

    @Override
    public boolean hmset(String key, Map<String, Object> map, long expireTime) {
        if (StringUtils.isNotBlank(key) && !CollectionUtils.isEmpty(map)) {
            this.redisTemplate.opsForHash().putAll(key, map);
            if (expireTime > 0) {
                this.expire(key, expireTime);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hset(String key, String item, Object value) {
        if (StringUtils.isNoneBlank(key, item) && value != null) {
            this.redisTemplate.opsForHash().put(key, item, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean hset(String key, String item, Object value, long expireTime) {
        if (StringUtils.isNoneBlank(key, item) && value != null) {
            this.redisTemplate.opsForHash().put(key, item, value);
            if (expireTime > 0) {
                this.expire(key, expireTime);
            }
            return true;
        }
        return false;
    }

    @Override
    public void hdel(String key, Object... item) {
        this.redisTemplate.opsForHash().delete(key, item);
    }

    @Override
    public Set<Object> sGet(String key) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.opsForSet().members(key) : null;
    }

    @Override
    public long sSet(String key, Object... values) {
        return StringUtils.isNotBlank(key) && ArrayUtils.isNotEmpty(values) ? this.redisTemplate.opsForSet().add(key, values) : OPERATE_FAIL;
    }

    @Override
    public long sSetAndTime(String key, long expireTime, Object... values) {
        Long count = this.redisTemplate.opsForSet().add(key, values);
        if (expireTime > 0) {
            this.expire(key, expireTime);
        }
        return count;
    }

    @Override
    public long setRemove(String key, Object... values) {
        return StringUtils.isNotBlank(key) && ArrayUtils.isNotEmpty(values) ? this.redisTemplate.opsForSet().remove(key, values) : OPERATE_FAIL;
    }

    @Override
    public List<Object> lGet(String key, long start, long end) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.opsForList().range(key, start, end) : null;
    }

    @Override
    public Object lGetIndtex(String key, long index) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.opsForList().index(key, index) : null;
    }

    @Override
    public boolean lSet(String key, Object value) {
        if (StringUtils.isNotBlank(key) && value != null) {
            this.redisTemplate.opsForList().rightPush(key, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean lSet(String key, Object value, long expireTime) {
        if (StringUtils.isNotBlank(key) && value != null) {
            this.redisTemplate.opsForList().rightPush(key, value);
            if (expireTime > 0) {
                this.expire(key, expireTime);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean lSet(String key, List<Object> value) {
        if (StringUtils.isNotBlank(key) && !CollectionUtils.isEmpty(value)) {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean lSet(String key, List<Object> value, long expireTime) {
        if (StringUtils.isNotBlank(key) && !CollectionUtils.isEmpty(value)) {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            if (expireTime > 0) {
                this.expire(key, expireTime);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean lUpdateIndex(String key, long index, Object value) {
        if (StringUtils.isNotBlank(key) && value != null) {
            this.redisTemplate.opsForList().set(key, index, value);
            return true;
        }
        return false;
    }

    @Override
    public long lRemove(String key, long count, Object value) {
        return StringUtils.isNotBlank(key) && value != null ? this.redisTemplate.opsForList().remove(key, count, value) : OPERATE_FAIL;
    }

    @Override
    public Set<Object> zrangeByScore(String key, double value, double value1) {
        return StringUtils.isNotBlank(key) ? this.redisTemplate.opsForZSet().rangeByScore(key, value, value1) : null;
    }
}
