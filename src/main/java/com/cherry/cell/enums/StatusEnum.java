package com.cherry.cell.enums;

import lombok.Getter;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午10:46
 */
@Getter
public enum StatusEnum {
    /**
     * 系统相关枚举
     */
    SUCCESS(0,"操作成功"),
    EXCEPTION(-1,"操作异常"),
    SYSTEM_EXCEPTION(-2,"系统异常"),
    SERVICE_EXCEPTION(-3,"业务异常"),

    /**
     * 商品相关枚举
     */
    UP(1, "在架"),
    DOWN(2, "下架");

    private Integer code;
    private String msg;

    StatusEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
