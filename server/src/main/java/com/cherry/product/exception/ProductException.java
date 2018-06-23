package com.cherry.product.exception;

import com.cherry.product.enums.ResultEnum;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/22 下午5:01
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(String message,Integer code){
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
