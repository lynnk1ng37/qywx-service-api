package com.qq.qywx.common.entity;

/**
 * Created by 72088532 on 2021/1/30.
 */

public class Header {
    private Integer hid;
    private String key;
    private String value;
    private String store_time;

    @Override
    public String toString() {
        return "Header{" +
                "hid=" + hid +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", store_time='" + store_time + '\'' +
                '}';
    }

    public Header(String key, String value, String store_time) {
        this.key = key;
        this.value = value;
        this.store_time = store_time;
    }

    public Header(Integer hid, String key, String value, String store_time) {
        this.hid = hid;
        this.key = key;
        this.value = value;
        this.store_time = store_time;
    }

    public Header() {
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
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
