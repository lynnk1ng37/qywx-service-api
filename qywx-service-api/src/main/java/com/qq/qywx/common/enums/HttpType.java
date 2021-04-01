package com.qq.qywx.common.enums;

/**
 * 请求类型
 * Created by 72088532 on 2021/1/1.
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
