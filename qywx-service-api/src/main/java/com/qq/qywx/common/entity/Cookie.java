package com.qq.qywx.common.entity;

/**
 * Created by 72088532 on 2021/2/20.
 */

public class Cookie {
    private Integer hid;
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

    public Cookie(Integer hid, String api_id, String key, String value, String store_time) {
        this.hid = hid;
        this.api_id = api_id;
        this.key = key;
        this.value = value;
        this.store_time = store_time;
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "hid=" + hid +
                ", api_id='" + api_id + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", store_time='" + store_time + '\'' +
                '}';
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
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
