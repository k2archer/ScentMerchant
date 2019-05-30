package com.kwei.scentmerchant.model.retrofit;

import com.kwei.scentmerchant.model.bean.BaseMessage;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    public static final String BASE_URL = "http://192.168.0.153:8080/ScentMerchantWeb/";
    private static ApiService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService.class);

    public static ApiService getApiService() {
        return apiService;
    }

    public static void subscribe(Observable<BaseMessage> observable, Observer<BaseMessage> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
