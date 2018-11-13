package com.headerits.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

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
public class AsyncTaskTest1 {

    @Async
    public Future<String> sendData(String name) {
        log.info("sendData method call");
        try {
            Thread.sleep(5000);
            log.info("sendData 业务逻辑执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String demo = "spring async :" + name;
        return new AsyncResult<>(demo);
    }
}
