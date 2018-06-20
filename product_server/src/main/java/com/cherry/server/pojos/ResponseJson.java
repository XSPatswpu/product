package com.cherry.server.pojos;

import lombok.Data;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/6/10 下午9:45
 */
@Data
public class ResponseJson {
    public ResponseJson(){}

    public ResponseJson(Integer code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Integer code;// 状态值，参考异常状态列表
    private String msg;// 描述
    private Object data;// 业务数据
}

