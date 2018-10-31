package com.headerits.startup.v2;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * <p> Description: </p>
 * <p> Title: BaseListener </p>
 * <p> Create Time: 2018/10/30 9:02 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Slf4j
@Component
public class BaseListener {

    @EventListener
    public void systemStartUp(ContextRefreshedEvent event) {
        log.info("系统启动....");
    }

}
