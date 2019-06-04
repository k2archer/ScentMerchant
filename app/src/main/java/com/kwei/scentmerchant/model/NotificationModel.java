package com.kwei.scentmerchant.model;

import com.kwei.scentmerchant.contract.NotificationContract;
import com.kwei.scentmerchant.model.bean.NotificationResponseBody;
import com.kwei.scentmerchant.model.bean.base.BaseResponseBody;
import com.kwei.scentmerchant.model.retrofit.ExceptionUtils;
import com.kwei.scentmerchant.model.retrofit.RetrofitFactory;
import com.kwei.scentmerchant.presenter.NotificationPresenter;
import com.kwei.scentmerchant.utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NotificationModel implements NotificationContract.Model {
    @Override
    public void getNotificationList(NotificationPresenter presenter) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", TokenUtils.getToken());
//        requestBody.put("merchant_id", merchantId);
        requestBody.put("page_size", "6"); // 分页请求

        Observable<NotificationResponseBody> observable;
        observable = RetrofitFactory.getApiService().getNotificationList(requestBody);

        Observer<NotificationResponseBody> observer = new Observer<NotificationResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NotificationResponseBody body) {
                if (body.code == BaseResponseBody.SUCCESS_CODE) {
                    presenter.updateList(body.data);
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
