package com.hengchang.lib_base.net.http.convert;


import com.alibaba.fastjson.JSONObject;
import com.hengchang.lib_base.utils.AES;
import com.hengchang.lib_base.utils.LogUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @author zhangzhilong
 * @date 2019/4/30.
 * description：
 */
class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        String paramJson = JSONObject.toJSONString(value);
        LogUtils.json("OkHttp","RequestBody : " + paramJson);
        return RequestBody.create(MEDIA_TYPE, AES.encrypt(paramJson));
    }
}
