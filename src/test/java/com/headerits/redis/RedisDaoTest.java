package com.headerits.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p> Description: redis 测试类 </p>
 * <p> Title: RedisDaoTest </p>
 * <p> Create Time: 2018/10/30 14:50 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class RedisDaoTest {

    @Autowired
    private RedisDao<Object> jdkRedisDao;

    @Autowired
    private RedisDao<String> stringRedisDao;

    @Test
    public void expire1() throws Exception {

    }

    @Test
    public void getExpire() throws Exception {

    }

    @Test
    public void hasKey() throws Exception {

    }

    @Test
    public void del() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void set() throws Exception {
        this.stringRedisDao.set("zzp11", "钟志鹏");

    }

    @Test
    public void set1() throws Exception {

    }

    @Test
    public void incr() throws Exception {

    }

    @Test
    public void decr() throws Exception {

    }

    @Test
    public void hget() throws Exception {

    }

    @Test
    public void hmget() throws Exception {

    }

    @Test
    public void hmset() throws Exception {

    }

    @Test
    public void hmset1() throws Exception {

    }

    @Test
    public void hset() throws Exception {

    }

    @Test
    public void hset1() throws Exception {

    }

    @Test
    public void hdel() throws Exception {

    }

    @Test
    public void hHasKey() throws Exception {

    }

    @Test
    public void hincr() throws Exception {

    }

    @Test
    public void hdecr() throws Exception {

    }

    @Test
    public void sGet() throws Exception {

    }

    @Test
    public void sHasKey() throws Exception {

    }

    @Test
    public void sSet() throws Exception {

    }

    @Test
    public void sSetAndTime() throws Exception {

    }

    @Test
    public void sGetSetSize() throws Exception {

    }

    @Test
    public void setRemove() throws Exception {

    }

    @Test
    public void lGet() throws Exception {

    }

    @Test
    public void lGetListSize() throws Exception {

    }

    @Test
    public void lGetIndtex() throws Exception {

    }

    @Test
    public void lSet() throws Exception {

    }

    @Test
    public void lSet1() throws Exception {

    }

    @Test
    public void lSet2() throws Exception {

    }

    @Test
    public void lSet3() throws Exception {

    }

    @Test
    public void lUpdateIndex() throws Exception {

    }

    @Test
    public void lRemove() throws Exception {

    }

    @Test
    public void zrangeByScore() throws Exception {

    }

    @Test
    public void expire() throws Exception {

    }

}