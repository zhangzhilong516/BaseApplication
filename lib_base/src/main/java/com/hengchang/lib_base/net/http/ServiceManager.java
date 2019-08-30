package com.hengchang.lib_base.net.http;

import com.hengchang.lib_base.net.http.convert.JsonConverterFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * @author zhangzhilong
 * @date 2019/8/29.
 * description：
 */
public class ServiceManager {

    private static final Map<String, Object> mServiceMap = new ConcurrentHashMap<>();

    /**
     * 获取ApiService
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> serviceClass) {
        return getService(HttpConfig.BASE_URL, serviceClass);
    }

    /**
     * 获取ApiService
     *
     * @param domain
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getService(String domain, Class<T> serviceClass) {
        synchronized (ServiceManager.class) {
            Object apiService = mServiceMap.get(domain);
            if (apiService == null) {
                apiService = getRetrofit(domain)
                        .create(serviceClass);
                mServiceMap.put(domain, apiService);
            }
            return (T) apiService;
        }
    }


    /**
     * 创建Retrofit
     *
     * @param baseUrl
     * @return
     */
    public static Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpManager.getInstance().getOkHttp())
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
