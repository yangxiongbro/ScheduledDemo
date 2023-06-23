package com.example.consumer.ms;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "Producer", path = "/producer/producer")
public interface ProducerFeignService {
    @GetMapping("/increment")
    Boolean incrementInventory();
}