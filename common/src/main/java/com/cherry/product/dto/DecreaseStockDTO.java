package com.cherry.product.dto;

import lombok.Data;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/22 下午4:36
 */
@Data
public class DecreaseStockDTO {
    private String productId;
    private Integer decreaseStock;
    public DecreaseStockDTO(){}

    public DecreaseStockDTO(String productId, Integer decreaseStock) {
        this.productId = productId;
        this.decreaseStock = decreaseStock;
    }
}
