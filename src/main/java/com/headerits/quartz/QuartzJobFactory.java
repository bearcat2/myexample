package com.headerits.quartz;

import com.headerits.util.system.ApplicationContextHelper;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleService simpleService = ApplicationContextHelper.getBean(SimpleService.class);
        simpleService.testMethod();
        Quartz1 quartz1 = ApplicationContextHelper.getBean(Quartz1.class);
        quartz1.test();
    }
}
