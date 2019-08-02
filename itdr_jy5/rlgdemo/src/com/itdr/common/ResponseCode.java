package com.itdr.common;

public class ResponseCode<T> {
    private  Integer status;
    private  T data;
    private  String mag;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    //成功的时候只要返回状态码和成功获取的数据
    //失败的时候只要返回状态码和失败信息

}
