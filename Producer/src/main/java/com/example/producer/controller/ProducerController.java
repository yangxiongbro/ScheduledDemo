package com.example.producer.controller;

import com.example.producer.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/producer")
@RestController
public class ProducerController {
    @Autowired
    private IProductService productService;

    @GetMapping("/increment")
    public Boolean incrementInventory(Integer id){
        return productService.incrementInventory(id);
    }
}
