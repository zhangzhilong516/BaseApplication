package com.hengchang.baseapplication;


import com.hengchang.lib_base.net.http.result.Result;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author zhangzhilong
 * @date 2019/8/28.
 * description：
 */
public interface ApiService {
    @POST("cashloan-api")
    @Headers("base_url" + ":shengchan")
    Observable<Result> refreshToken(@Body RequestBody requestBody);

}
