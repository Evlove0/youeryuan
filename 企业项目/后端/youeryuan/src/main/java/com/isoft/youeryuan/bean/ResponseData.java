package com.isoft.youeryuan.bean;

import lombok.Data;

/**
 * 返回客户端数据类
 */
@Data
public class ResponseData<T> {
    private Integer errCode ;
    private String errMsg ;
    /**
     * 返回客户端有效数据
     */
    private T data ;


    public ResponseData(Integer errCode, String errMsg, T data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

}
