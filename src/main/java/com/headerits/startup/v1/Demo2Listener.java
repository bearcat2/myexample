package com.headerits.startup.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p> Description: </p>
 * <p> Title: Demo1Listener </p>
 * <p> Create Time: 2018/10/30 9:03 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Slf4j
@Order(1)
@Component
public class Demo2Listener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Demo2Listener 监听启动...");
    }
}
