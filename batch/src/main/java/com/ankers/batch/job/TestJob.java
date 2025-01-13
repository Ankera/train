package com.ankers.batch.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TestJob TEST开始");
        Long start = System.currentTimeMillis();
         try {
             Thread.sleep(10000);
             Long duration = System.currentTimeMillis() - start;
             System.out.println("duration==>" + duration);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
        System.out.println("TestJob TEST结束");
    }
}
