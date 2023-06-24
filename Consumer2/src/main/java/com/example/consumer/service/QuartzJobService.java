package com.example.consumer.service;

import com.example.consumer.ms.ProducerFeignService;
import com.example.consumer.service.interfaces.IQuartzJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzJobService implements IQuartzJobService, Job {
    @Autowired
    private ProducerFeignService producerFeignService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Boolean result = producerFeignService.incrementInventory(2);
        System.out.println("Quartz:" + result);
    }
}
