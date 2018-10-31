package com.headerits.java.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: </p>
 * <p>Title: DemoLog </p>
 * <p>Create Time: 2018/9/5 21:06 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class DemoLog {

    private static final Logger LOGGER = LoggerFactory.getLogger("sencodLogger");
    private static final Logger LOGGER1 = LoggerFactory.getLogger(DemoLog.class);

    @Test
    public void test() throws Exception {
        LOGGER.info("测试11112");
        LOGGER.warn("测试11112");
        LOGGER1.info("根测试11112");
    }


}
