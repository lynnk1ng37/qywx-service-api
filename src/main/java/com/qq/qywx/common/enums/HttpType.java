package com.qq.qywx.common.enums;

/**
 * 请求类型
 * Created by lynnking.
 */

public enum HttpType {
    // post枚举
    POST("post"),
    // get枚举
    GET("get");

    private String value;

    HttpType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
