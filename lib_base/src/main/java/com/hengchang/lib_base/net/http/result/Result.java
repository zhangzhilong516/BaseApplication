package com.hengchang.lib_base.net.http.result;

/**
 * @author zhangzhilong
 * @date 2019/4/19.
 * description：
 */
public class Result<T> extends BaseResult {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
