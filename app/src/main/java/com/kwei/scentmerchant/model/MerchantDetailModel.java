package com.kwei.scentmerchant.model;

import com.google.gson.Gson;
import com.kwei.scentmerchant.contract.MerchantDetailContract;
import com.kwei.scentmerchant.model.bean.MerchantDetailResponseBody;
import com.kwei.scentmerchant.model.bean.MerchantSubmitRequestBody;
import com.kwei.scentmerchant.model.bean.base.BaseResponseBody;
import com.kwei.scentmerchant.model.retrofit.ExceptionUtils;
import com.kwei.scentmerchant.model.retrofit.RetrofitFactory;
import com.kwei.scentmerchant.utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MerchantDetailModel implements MerchantDetailContract.Model {
    @Override
    public void getMerchantDetail(MerchantDetailContract.Presenter presenter) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", TokenUtils.getToken());
//        requestBody.put("merchant_id", merchantId);

        Observable<MerchantDetailResponseBody> observable;
        observable = RetrofitFactory.getApiService().getMerchant(requestBody);

        Observer<MerchantDetailResponseBody> observer = new Observer<MerchantDetailResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MerchantDetailResponseBody body) {
                if (body.code == BaseResponseBody.SUCCESS_CODE) {
                    if (body.data != null) {
                        presenter.showMerchantDetail(body.data);
                    }
                } else {
                    presenter.showToast(body.message);
                }
            }

            @Override
            public void onError(Throwable e) {
                presenter.onFail(ExceptionUtils.handleException(e));
            }

            @Override
            public void onComplete() {

            }
        };

        RetrofitFactory.subscribe(observable, observer);
    }

    @Override
    public void submitVerify(MerchantDetailContract.Presenter presenter) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", TokenUtils.getToken());
        MerchantSubmitRequestBody submitRequestBody = new MerchantSubmitRequestBody();
        submitRequestBody.id = "merchant_id";
        requestBody.put("merchant", new Gson().toJson(submitRequestBody));

        Observable<BaseResponseBody> observable;
        observable = RetrofitFactory.getApiService().submitMerchant(requestBody);

        Observer<BaseResponseBody> observer = new Observer<BaseResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBody body) {
                if (body.code == BaseResponseBody.SUCCESS_CODE) {
                    presenter.onSubmitSucceed();
                } else {
                    presenter.showToast(body.message);
                }
            }

            @Override
            public void onError(Throwable e) {
                presenter.onFail(ExceptionUtils.handleException(e));
            }

            @Override
            public void onComplete() {

            }
        };

        RetrofitFactory.subscribe(observable, observer);
    }
}
