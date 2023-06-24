package com.example.consumer.service;

import com.example.consumer.ms.ProducerFeignService;
import com.example.consumer.service.interfaces.IXxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XxlJobService implements IXxlJobService {
    @Autowired
    private ProducerFeignService producerFeignService;

    @XxlJob("testJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        Boolean result = producerFeignService.incrementInventory(3);
        System.out.println("XxlJob:" + result);
        return ReturnT.SUCCESS;
    }
}
