package com.headerits.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 * <p>Description: </p>
 * <p>Title: DemoScheduled </p>
 * <p>Create Time: 2018/8/24 16:33 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
//@Component
public class DemoScheduled implements SchedulingConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoScheduled.class);

    private static String cronExpression;

    public DemoScheduled() {
        LOGGER.info("执行数据库查询....");
        cronExpression = "0/2 * * * * ?";
    }

    public static void setCronExpression(String cronExpression) {
        DemoScheduled.cronExpression = cronExpression;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("业务逻辑执行。。。cornExpression 为 = {} ", cronExpression);
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger cronTrigger = new CronTrigger(cronExpression);
                Date nextExecutionTime = cronTrigger.nextExecutionTime(triggerContext);
                LOGGER.info("cornExpression = {}", cronExpression);
                return nextExecutionTime;
            }
        });
    }
}

