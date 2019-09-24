package com.isoft.youeryuan.bean;

public class TestException extends Exception {
    private Integer code = 222;
    public TestException(String msg) {
        super(msg);
    }
    public TestException(){}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer errCode) {
        this.code = errCode;
    }
}
