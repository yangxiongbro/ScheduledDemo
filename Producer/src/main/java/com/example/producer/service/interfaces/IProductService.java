package com.example.producer.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.po.ProductPO;

public interface IProductService extends IService<ProductPO> {

    Boolean incrementInventory(Integer id);
}
