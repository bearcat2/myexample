package com.headerits.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * <p> Description: </p>
 * <p> Title: Demo1Scheduled </p>
 * <p> Create Time: 2018/10/23 8:53 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
//@Component
public class Demo1Scheduled {
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo1Scheduled.class);

    @Scheduled(fixedDelay = 1000)
    public void demo() {
        LOGGER.info(" demo 执行了 。。。");
    }

    @Scheduled(fixedDelay = 1500)
    public void demo1() {
        LOGGER.info(" demo1 执行了 。。。");
    }
}
