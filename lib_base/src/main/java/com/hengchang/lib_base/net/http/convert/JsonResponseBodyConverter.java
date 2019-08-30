package com.hengchang.lib_base.net.http.convert;

import com.alibaba.fastjson.JSONObject;
import com.hengchang.lib_base.utils.AES;
import com.hengchang.lib_base.utils.LogUtils;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author zhangzhilong
 * @date 2019/4/30.
 * description：
 */
class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private Type type;

    public JsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = AES.decrypt(value.string());

        LogUtils.json("OkHttp", "ResponseBody : " + response);

        T result = JSONObject.parseObject(response, type);


        return result;
    }
}
