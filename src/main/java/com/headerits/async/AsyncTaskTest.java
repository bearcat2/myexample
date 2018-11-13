package com.headerits.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <p> Description: 异步任务测试 </p>
 * <p> Title: AsyncTaskTest </p>
 * <p> Create Time: 2018/11/7 14:15 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Slf4j
@Component
public class AsyncTaskTest {
    private static ConcurrentHashMap<String, Future<String>> concurrentHashMap = new ConcurrentHashMap<>();

    @Autowired
    private AsyncTaskTest1 asyncTaskTest1;

    public void append(String key, String value) {
        log.info("append start");
        Future<String> stringFuture = this.asyncTaskTest1.sendData(value);
        concurrentHashMap.put(key, stringFuture);
        log.info("append end");
    }

    //@Scheduled(fixedDelay = 1000)
    public void execute() {
        for (Map.Entry<String, Future<String>> entry : concurrentHashMap.entrySet()) {
            Future value = entry.getValue();
            if (value.isCancelled() || value.isDone()) {
                try {
                    log.info("异步结果 {}", value.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                log.info("没有获取到结果...");
            }
        }
    }
}
