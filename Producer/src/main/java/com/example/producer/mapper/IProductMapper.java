package com.example.producer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.po.ProductPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IProductMapper extends BaseMapper<ProductPO> {

}