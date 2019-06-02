package com.kwei.scentmerchant.model;

import com.kwei.scentmerchant.contract.StatisticsContract;
import com.kwei.scentmerchant.model.bean.StatisticsResponseBody;
import com.kwei.scentmerchant.model.bean.base.BaseResponseBody;
import com.kwei.scentmerchant.model.retrofit.ExceptionUtils;
import com.kwei.scentmerchant.model.retrofit.RetrofitFactory;
import com.kwei.scentmerchant.utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class StatisticsModel implements StatisticsContract.Model {
    @Override
    public void getStatistics(StatisticsContract.Presenter presenter) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", TokenUtils.getToken());

        Observable<StatisticsResponseBody> observable;
        observable = RetrofitFactory.getApiService().getStatistics(requestBody);

        Observer<StatisticsResponseBody> observer = new Observer<StatisticsResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StatisticsResponseBody statisticsBody) {
                if (statisticsBody.code == BaseResponseBody.SUCCESS_CODE) {
                    presenter.showStatistics(statisticsBody.data);
                } else {
                    presenter.showToast(statisticsBody.message);
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
