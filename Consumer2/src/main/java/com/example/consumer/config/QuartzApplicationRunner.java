package com.example.consumer.config;

import com.example.consumer.service.QuartzJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class QuartzApplicationRunner implements ApplicationRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startJob();
    }

    public void startJob() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(QuartzJobService.class)
                .withIdentity("testJob", "testJobGroup")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testTrigger", "testTriggerGroup")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        // 使用触发器调度任务的执行
        scheduler.scheduleJob(job, trigger);
        // 开启任务
        scheduler.start();
    }
}
