package com.kwei.scentmerchant.model;

import com.kwei.scentmerchant.contract.MerchantContract;
import com.kwei.scentmerchant.model.bean.MerchantAccountResponseBody;
import com.kwei.scentmerchant.model.bean.SettlementListBody;
import com.kwei.scentmerchant.model.bean.base.BaseResponseBody;
import com.kwei.scentmerchant.model.retrofit.ExceptionUtils;
import com.kwei.scentmerchant.model.retrofit.RetrofitFactory;
import com.kwei.scentmerchant.utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MerchantModel implements MerchantContract.Model {
    @Override
    public void getMerchantAccount(MerchantContract.Presenter presenter) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", TokenUtils.getToken());
//        requestBody.put("merchant_id", merchantId);

        Observable<MerchantAccountResponseBody> observable;
        observable = RetrofitFactory.getApiService().getMerchantAccount(requestBody);

        Observer<MerchantAccountResponseBody> observer = new Observer<MerchantAccountResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MerchantAccountResponseBody body) {
                if (body.code == BaseResponseBody.SUCCESS_CODE) {
                    presenter.showAccount(body.data);
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
    public void getSettlementList(MerchantContract.Presenter presenter) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", TokenUtils.getToken());
//        requestBody.put("merchant_id", merchantId);
        requestBody.put("page_size", "6"); // 分页请求

        Observable<SettlementListBody> observable;
        observable = RetrofitFactory.getApiService().getSettlementList(requestBody);

        Observer<SettlementListBody> observer = new Observer<SettlementListBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SettlementListBody body) {
                if (body.code == BaseResponseBody.SUCCESS_CODE) {
                    if (body.data != null) {
                        presenter.updateList(body.data);
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
}
