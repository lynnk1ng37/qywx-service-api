package com.qq.qywx.common.entity;

import com.qq.qywx.common.enums.HttpType;

/**
 * Created by lynnking.
 */

public class TestStep {

    /**
     * 请求类型
     */
    private HttpType type;
    /**
     * 接口path
     */
    private String path;
    /**
     * 请求携带的参数，key、value格式
     */
//    private Map<String, Object> params = new HashMap<>();

    private String params;

    /**
     * 消息体
     */
    private String body;

    public HttpType getType() {
        return type;
    }

    public void setType(HttpType type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
