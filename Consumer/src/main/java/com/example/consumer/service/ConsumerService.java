package com.example.consumer.service;

import com.example.consumer.ms.ProducerFeignService;
import com.example.consumer.service.interfaces.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService implements IConsumerService {
    @Autowired
    private ProducerFeignService producerFeignService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void autoIncrementInventory(){
        Boolean result = producerFeignService.incrementInventory();
        System.out.println(result);
    }
}
