package com.example.common.vo;

import lombok.Data;

@Data
public class ProductVO {
    private Long id;
    private String productName;
    private Integer status;
    private Double price;
    private String productDesc;
    private String caption;
    private Integer inventory;
}
