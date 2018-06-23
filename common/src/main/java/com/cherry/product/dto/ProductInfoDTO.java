package com.cherry.product.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/21 下午8:50
 */
@Data
public class ProductInfoDTO {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private String productDescription;
    private String productIcon;
    private Integer productStock;
    private Integer categoryType;//类目编号
    private Integer productStatus;
}
