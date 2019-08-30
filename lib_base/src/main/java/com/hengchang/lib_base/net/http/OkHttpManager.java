package com.hengchang.lib_base.net.http;

import com.hengchang.lib_base.net.http.interceptor.BaseUrlInterceptor;
import com.hengchang.lib_base.net.http.interceptor.HttpLoggingInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * @author zhangzhilong
 * @date 2019/8/28.
 * description：
 */
public class OkHttpManager {
    private static final int TIME_OUT = 15;

    private List<Interceptor> interceptors;
    private List<Interceptor> networkInterceptors;

    private static class Holder {
        public static final OkHttpManager INSTANCE = new OkHttpManager();
    }

    public static OkHttpManager getInstance() {
        return Holder.INSTANCE;
    }


    public OkHttpClient getOkHttp() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new BaseUrlInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        if (interceptors != null && !interceptors.isEmpty()) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        if (networkInterceptors != null && !networkInterceptors.isEmpty()) {
            for (Interceptor interceptor : networkInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        return builder.build();
    }

    public void addNetworkInterceptor(Interceptor interceptor) {
        if (networkInterceptors == null) {
            networkInterceptors = new ArrayList<>();
        }
    }

    public void addInterceptor(Interceptor interceptor) {
        if (interceptors == null) {
            interceptors = new ArrayList<>();
        }
    }

}
