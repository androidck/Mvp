package com.rxjava.mvp.common.base;

import com.rxjava.mvp.common.uitl.MainUtil;

/**
 * @author: Allen.
 * @date: 2018/7/25
 * @description:
 */

public class BaseEntry<T> {

    private int code;
    private String msg;
    private T newslist;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess(){
        return getCode()== MainUtil.SUCCESS_CODE;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getNewslist() {
        return newslist;
    }

    public void setNewslist(T newslist) {
        this.newslist = newslist;
    }
}
