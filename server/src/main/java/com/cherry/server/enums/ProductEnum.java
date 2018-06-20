package com.cherry.server.enums;

import lombok.Getter;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/19 下午10:30
 */
@Getter
public enum ProductEnum {
    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String msg;

    ProductEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
