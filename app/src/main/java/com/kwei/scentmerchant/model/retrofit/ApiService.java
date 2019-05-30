package com.kwei.scentmerchant.model.retrofit;


import com.kwei.scentmerchant.model.bean.BaseMessage;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface ApiService {
    @POST("login")
    @FormUrlEncoded
    Observable<BaseMessage> login(@Field("body")String body);

    @POST("requestVerifyCode")
    @FormUrlEncoded
    Observable<BaseMessage> requestVerifyCode(@Field("body")String body);

    @Multipart
    @POST("addNewShop")
    Observable<BaseMessage> addNewShop(@Part List<MultipartBody.Part> partList);

    @FormUrlEncoded
    @POST()
    Observable<BaseMessage> activateSticker(@Url String url, @Field("body") String body);

}
