package com.example.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_product")
public class ProductPO {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String productName;
    private Integer status;
    private Double price;
    private String productDesc;
    private String caption;//标题
    private Integer inventory;//库存
}

