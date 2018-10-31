package com.headerits.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleService.class);

    public void testMethod() {
        // 这里执行定时调度业务
        LOGGER.info("testMethod 方法执行  ====== ");
    }
}
