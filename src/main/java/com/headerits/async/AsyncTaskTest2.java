package com.headerits.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
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
public class AsyncTaskTest2 {

    private static ConcurrentHashMap<String, Queue<String>> runtimeMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Future<HttpResult>> resultMap = new ConcurrentHashMap<>();

    public AsyncTaskTest2() {
        Thread thread = new Thread(new AsyncResultHandlerRunnable(), "异步任务结果处理线程");
        thread.start();
    }

    public void append(String key, String message) {
        Queue<String> queue = null;
        if (!runtimeMap.containsKey(key)) {
            queue = new ConcurrentLinkedQueue<>();
        } else {
            queue = runtimeMap.get(key);
        }
        queue.add(message);
    }

    public void taskCheck() {
        for (Map.Entry<String, Future<HttpResult>> entry : resultMap.entrySet()) {
            Future<HttpResult> value = entry.getValue();
            String key = entry.getKey();
            if (value.isCancelled() || value.isDone()) {
                try {
                    HttpResult httpResult = value.get();
                    Queue<String> queue = runtimeMap.get(key);
                    if (httpResult.getCode() == 200) {
                        queue.poll();
                    }else {
                        String peek = queue.peek();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

    final class AsyncResultHandlerRunnable implements Runnable {
        @Override
        public void run() {

        }
    }
}
