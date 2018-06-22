package com.cherry.product.pojos;


import com.cherry.product.enums.StatusEnum;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午10:26
 */
public class ResponseJsonBuilder {
    private ResponseJsonBuilder(){}

    private static ResponseJson build(int code, String desc, Object data) {
        return new ResponseJson(code,desc,data);
    }

    public static ResponseJson createFailOfSystem(String desc) {
        return build(StatusEnum.SYSTEM_EXCEPTION.getCode(),
                StatusEnum.SYSTEM_EXCEPTION.getMsg(),null);
    }

    public static ResponseJson createFailOfService(String desc) {
        return build(StatusEnum.SERVICE_EXCEPTION.getCode(),
                StatusEnum.SERVICE_EXCEPTION.getMsg(),null);
    }

    public static ResponseJson createSuccess(Object data) {
        return build(StatusEnum.SUCCESS.getCode(),
                StatusEnum.SUCCESS.getMsg(), data);
    }

    public static ResponseJson createSuccess() {
        return build(StatusEnum.SUCCESS.getCode(),
                StatusEnum.SUCCESS.getMsg(), null);
    }

}
