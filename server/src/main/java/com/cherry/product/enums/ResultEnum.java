package com.cherry.product.enums;

import lombok.Getter;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/22 下午5:23
 */
@Getter
public enum ResultEnum {
    PRODUCT_STOCK_ERROR(-1,"商品库存异常"),
    PRODUCT_NOT_EXIST(-2,"商品不存在");

    private Integer code;
    private String msg;

    ResultEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
