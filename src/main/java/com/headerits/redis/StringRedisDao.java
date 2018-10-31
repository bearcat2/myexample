package com.headerits.redis;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: 字符串类型redis数据访问对象</p>
 * <p>Title: StringRedisDao </p>
 * <p>Create Time: 2018/9/28 20:07 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Repository
public class StringRedisDao extends BaseRedisDao<String> {

    @Override
    public String get(String key) {
        return StringUtils.isNotBlank(key) ? stringRedisTemplate.opsForValue().get(key) : null;
    }

    @Override
    public boolean set(String key, String value) {
        if (StringUtils.isNoneBlank(key, value)) {
            this.stringRedisTemplate.opsForValue().set(key, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean set(String key, String value, long expireTime) {
        if (StringUtils.isNoneBlank(key, value)) {
            stringRedisTemplate.opsForValue().set(key, value, expireTime);
            return true;
        }
        return false;
    }

    @Override
    public boolean hmset(String key, Map<String, String> map) {
        if (StringUtils.isNotBlank(key) && !CollectionUtils.isEmpty(map)) {
            stringRedisTemplate.opsForHash().putAll(key, map);
            return true;
        }
        return false;
    }

    @Override
    public boolean hmset(String key, Map<String, String> map, long expireTime) {
        if (StringUtils.isNotBlank(key) && !CollectionUtils.isEmpty(map)) {
            stringRedisTemplate.opsForHash().putAll(key, map);
            if (expireTime > 0) {
                this.expire(key, expireTime);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hset(String key, String item, String value) {
        if (StringUtils.isNoneBlank(key, item, value)) {
            stringRedisTemplate.opsForHash().put(key, item, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean hset(String key, String item, String value, long expireTime) {
        if (StringUtils.isNoneBlank(key, item, value)) {
            stringRedisTemplate.opsForHash().put(key, item, value);
            if (expireTime > 0) {
                this.expire(key, expireTime);
            }
            return true;
        }
        return false;
    }

    @Override
    public void hdel(String key, String... item) {
        stringRedisTemplate.opsForHash().delete(key, item);
    }

    @Override
    public Set<String> sGet(String key) {
        return StringUtils.isNotBlank(key) ? stringRedisTemplate.opsForSet().members(key) : null;
    }

    @Override
    public long sSet(String key, String... values) {
        return StringUtils.isNotBlank(key) && ArrayUtils.isNotEmpty(values) ? stringRedisTemplate.opsForSet().add(key, values) : OPERATE_FAIL;
    }

    @Override
    public long sSetAndTime(String key, long expireTime, String... values) {
        Long count = stringRedisTemplate.opsForSet().add(key, values);
        if (expireTime > 0) {
            this.expire(key, expireTime);
        }
        return count;
    }

    @Override
    public long setRemove(String key, String... values) {
        return StringUtils.isNotBlank(key) && ArrayUtils.isNotEmpty(values) ? stringRedisTemplate.opsForSet().remove(key, values) : OPERATE_FAIL;
    }

    @Override
    public List<String> lGet(String key, long start, long end) {
        return StringUtils.isNotBlank(key) ? stringRedisTemplate.opsForList().range(key, start, end) : null;
    }

    @Override
    public String lGetIndtex(String key, long index) {
        return StringUtils.isNotBlank(key) ? stringRedisTemplate.opsForList().index(key, index) : null;
    }

    @Override
    public boolean lSet(String key, String value) {
        if (StringUtils.isNoneBlank(key, value)) {
            stringRedisTemplate.opsForList().rightPush(key, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean lSet(String key, String value, long expireTime) {
        if (StringUtils.isNoneBlank(key, value)) {
            stringRedisTemplate.opsForList().rightPush(key, value);
            if (expireTime > 0) {
                this.expire(key, expireTime);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean lSet(String key, List<String> value) {
        if (StringUtils.isNotBlank(key) && !CollectionUtils.isEmpty(value)) {
            stringRedisTemplate.opsForList().rightPushAll(key, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean lSet(String key, List<String> value, long expireTime) {
        if (StringUtils.isNotBlank(key) && !CollectionUtils.isEmpty(value)) {
            stringRedisTemplate.opsForList().rightPushAll(key, value);
            if (expireTime > 0) {
                this.expire(key, expireTime);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean lUpdateIndex(String key, long index, String value) {
        if (StringUtils.isNoneBlank(key, value)) {
            stringRedisTemplate.opsForList().set(key, index, value);
            return true;
        }
        return false;
    }

    @Override
    public long lRemove(String key, long count, String value) {
        return StringUtils.isNoneBlank(key, value) ? stringRedisTemplate.opsForList().remove(key, count, value) : OPERATE_FAIL;
    }

    @Override
    public Set<String> zrangeByScore(String key, double value, double value1) {
        return StringUtils.isNotBlank(key) ? stringRedisTemplate.opsForZSet().rangeByScore(key, value, value1) : null;
    }
}
