package com.kwei.scentmerchant.model.retrofit;


import com.kwei.scentmerchant.model.bean.BaseMessage;
import com.kwei.scentmerchant.model.bean.LoginBody;
import com.kwei.scentmerchant.model.bean.RequestVerifyCodeBody;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("service/v.1.0/login")
    Observable<BaseMessage> login(@Body LoginBody user);

    @POST("service/v.1.0/requestVerifyCode")
    Observable<BaseMessage> requestVerifyCode(@Body RequestVerifyCodeBody user);
}
