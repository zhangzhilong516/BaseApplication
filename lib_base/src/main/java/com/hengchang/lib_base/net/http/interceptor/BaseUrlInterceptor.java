package com.hengchang.lib_base.net.http.interceptor;

import android.text.TextUtils;

import com.hengchang.lib_base.net.http.HttpConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhangzhilong
 * @date 2019/8/28.
 * description：
 */
public class BaseUrlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String baseUrl = request.header(HttpConfig.HEADERS_BASE_URL);
        if (!TextUtils.isEmpty(baseUrl)) {
            HttpUrl baseURL = HttpUrl.parse(baseUrl);

            HttpUrl newHttpUrl = request.url().newBuilder()
                    .scheme(baseURL.scheme())//http协议如：http或者https
                    .host(baseURL.host())//主机地址
                    .port(baseURL.port())//端口
                    .build();

            request = request.newBuilder().url(newHttpUrl).build();
        }
        return chain.proceed(request);
    }
}
