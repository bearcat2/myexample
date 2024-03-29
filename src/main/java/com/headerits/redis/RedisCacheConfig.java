package com.headerits.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

/**
 * <p>Description: 网上找的通过spring管理redis实现注解式缓存</p>
 * <p>Title: RedisCacheConfig </p>
 * <p>Create Time: 2018/5/16 15:35 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    private volatile JedisConnectionFactory jedisConnectionFactory;

    private volatile RedisTemplate<String, String> redisTemplate;

    private volatile RedisCacheManager redisCacheManager;

    public RedisCacheConfig() {
        super();
    }

    /**
     * 带参数的构造方法 初始化所有的成员变量
     *
     * @param jedisConnectionFactory
     * @param redisTemplate
     * @param redisCacheManager
     */
    public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String, String> redisTemplate,
                            RedisCacheManager redisCacheManager) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.redisTemplate = redisTemplate;
        this.redisCacheManager = redisCacheManager;
    }

    public JedisConnectionFactory getJedisConnecionFactory() {
        return jedisConnectionFactory;
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public RedisCacheManager getRedisCacheManager() {
        return redisCacheManager;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(":");
                sb.append(method.getName());
                sb.append(":");
                for (Object obj : params) {
                    if(obj != null){
                        sb.append(obj.toString());
                    }
                }
                return sb.toString().replaceAll("\\.", "\\:");
            }
        };
    }
}
