package com.kwei.scentmerchant.model.retrofit;


import com.kwei.scentmerchant.model.bean.BaseMessage;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    @FormUrlEncoded
    Observable<BaseMessage> login(@Field("body")String body);

    @POST("requestVerifyCode")
    @FormUrlEncoded
    Observable<BaseMessage> requestVerifyCode(@Field("body")String body);
}
