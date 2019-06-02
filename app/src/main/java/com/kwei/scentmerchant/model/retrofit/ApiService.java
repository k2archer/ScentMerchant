package com.kwei.scentmerchant.model.retrofit;

import com.kwei.scentmerchant.model.bean.BaseMessage;
import com.kwei.scentmerchant.model.bean.MerchantAccountResponseBody;
import com.kwei.scentmerchant.model.bean.StatisticsListResponseBody;
import com.kwei.scentmerchant.model.bean.StatisticsResponseBody;
import com.kwei.scentmerchant.model.bean.StickerResponseBody;
import com.kwei.scentmerchant.model.bean.base.BaseResponseBody;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @POST("login")
    @FormUrlEncoded
    Observable<BaseMessage> login(@Field("body") String body);

    @POST("requestVerifyCode")
    @FormUrlEncoded
    Observable<BaseMessage> requestVerifyCode(@Field("body") String body);

    @Multipart
    @POST("addNewShop")
    Observable<BaseMessage> addNewShop(@Part List<MultipartBody.Part> partList);

    @FormUrlEncoded
    @POST("activeSticker")
    Observable<BaseResponseBody> activateSticker(@Field("body") String body);

    @POST("getStickerDetail")
    @FormUrlEncoded
    Observable<StickerResponseBody> getStickerDetail(@Field("body") String body);

    @POST("getSticker")
    @FormUrlEncoded
    Observable<StickerResponseBody> getSticker(@FieldMap Map<String, String> body);

    @POST("getStatistics")
    @FormUrlEncoded
    Observable<StatisticsResponseBody> getStatistics(@FieldMap Map<String, String> body);

    @POST("getStatisticsPage")
    @FormUrlEncoded
    Observable<StatisticsListResponseBody> getStatisticsPage(@FieldMap Map<String, String> body);

    @POST("getMerchantAccount")
    @FormUrlEncoded
    Observable<MerchantAccountResponseBody> getMerchantAccount(@FieldMap Map<String, String> body);
}
