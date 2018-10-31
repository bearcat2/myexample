package com.headerits.httpclient;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: 清除http空闲连接
 * 参考网址: https://blog.csdn.net/yee_c/article/details/54266924
 * </p>
 * <p>Title: IdleConnectionEvictor </p>
 * <p>Create Time: 2018/5/27 11:09 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class IdleConnectionEvictor extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(IdleConnectionEvictor.class);

    private final HttpClientConnectionManager connectionManager;

    private Integer maxIdleTime;

    private volatile boolean shutdown;

    public IdleConnectionEvictor(HttpClientConnectionManager connectionManager, Integer maxIdleTime) {
        this.connectionManager = connectionManager;
        this.maxIdleTime = maxIdleTime;
        this.start(); //启动run方法
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(maxIdleTime);
                    // 关闭失效的连接
                    connectionManager.closeExpiredConnections();
                }
            }
        } catch (Exception e) {
            logger.error("释放空闲连接出错了", e);
        }
    }

    /**
     * 销毁释放资源
     */
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
