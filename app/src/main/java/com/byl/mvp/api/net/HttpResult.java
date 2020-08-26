package com.byl.mvp.api.net;

/**
 * data为一个对象时使用
 *
 * @param <T>
 */
public class HttpResult<T> {
    /**
     * 错误码
     */
    public int errorCode;
    /**
     * 错误信息
     */
    public String errorMessage;
    /**
     * 返回消息主题
     */
    public T result;

}
