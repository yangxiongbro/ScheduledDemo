package com.example.producer.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.po.ProductPO;
import com.example.producer.mapper.IProductMapper;
import com.example.producer.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServiceImpl<IProductMapper, ProductPO> implements IProductService {

    public synchronized Boolean incrementInventory(Integer id){
        boolean result = false;
        ProductPO product = getById(id);
        if(null != product){
            Integer inventory = product.getInventory();
            System.out.println(inventory + " + " + 1);
            product.setInventory(inventory + 1);
            result = updateById(product);
        }
        return result;
    }
}
