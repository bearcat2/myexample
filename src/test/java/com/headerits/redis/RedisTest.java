package com.headerits.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * <p> Description: reids 测试类 </p>
 * <p> Title: RedisTest </p>
 * <p> Create Time: 2018/11/9 14:07 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class RedisTest {

    @Test
    public void testCluster() throws Exception {
        Set<HostAndPort> hostAndPorts = new HashSet<>(6);
        hostAndPorts.add(new HostAndPort("192.168.88.130", 9001));
        hostAndPorts.add(new HostAndPort("192.168.88.130", 9002));
        hostAndPorts.add(new HostAndPort("192.168.88.130", 9003));
        hostAndPorts.add(new HostAndPort("192.168.88.130", 9004));
        hostAndPorts.add(new HostAndPort("192.168.88.130", 9005));
        hostAndPorts.add(new HostAndPort("192.168.88.130", 9006));
        JedisCluster jedisCluster = new JedisCluster(hostAndPorts);
        jedisCluster.set("cluster1", "demo2");
        System.out.println(jedisCluster.get("cluster1"));
        jedisCluster.close();
    }
}
