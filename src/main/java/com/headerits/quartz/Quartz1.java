package com.headerits.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 * <p>Title: Quartz1 </p>
 * <p>Create Time: 2018/8/24 16:11 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Component
public class Quartz1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Quartz1.class);

    public void test() {
        LOGGER.info("Quartz1 === 执行");
    }
}
