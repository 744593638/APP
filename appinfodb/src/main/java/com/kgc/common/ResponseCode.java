package com.kgc.common;

public enum  ResponseCode {
    /**
     * 成功
     */
    SUCCESS(1,"success"),

    /**
     * 失败
     */
    FAIL(-1,"fail"),
    /**
     * 参数丢失
     */
    PARAMETER_IS_NOT_FOUND(-2,"参数丢失"),
    /**
     * 用户未登录
     */
    USER_NEED_LOGIN(-3,"用户需要登录");

    private Integer code;

    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
