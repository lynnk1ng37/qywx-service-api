package com.qq.qywx.common.entity;

/**
 * 存库的请求Cookie
 * Created by lynnking.
 */

public class Cookie {
    private Integer cid;
    private String api_id;
    private String key;
    private String value;
    private String store_time;

    public Cookie() {
    }

    public Cookie(String api_id, String key, String value, String store_time) {
        this.api_id = api_id;
        this.key = key;
        this.value = value;
        this.store_time = store_time;
    }

    public Cookie(Integer cid, String api_id, String key, String value, String store_time) {
        this.cid = cid;
        this.api_id = api_id;
        this.key = key;
        this.value = value;
        this.store_time = store_time;
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "cid=" + cid +
                ", api_id='" + api_id + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", store_time='" + store_time + '\'' +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStore_time() {
        return store_time;
    }

    public void setStore_time(String store_time) {
        this.store_time = store_time;
    }
}
