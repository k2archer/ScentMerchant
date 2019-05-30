package com.kwei.scentmerchant.model;

import com.google.gson.Gson;
import com.kwei.scentmerchant.contract.StickerContract;
import com.kwei.scentmerchant.model.bean.ActiveRequest;
import com.kwei.scentmerchant.model.bean.BaseMessage;
import com.kwei.scentmerchant.model.retrofit.ExceptionUtils;
import com.kwei.scentmerchant.model.retrofit.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class StickerModelImpl implements StickerContract.Model {

    @Override
    public void getStickerDetail(String StickerId, StickerContract.StickerPresenter presenter) {

    }

    @Override
    public void activateSticker(String url, StickerContract.StickerPresenter presenter) {

        Gson gson = new Gson();
        ActiveRequest request = new ActiveRequest();
        request.token = "token1";

        Observable<BaseMessage> observable = RetrofitFactory.getApiService().activateSticker(url, gson.toJson(request));

        Observer<BaseMessage> observer = new Observer<BaseMessage>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseMessage baseMessage) {
                if (baseMessage.isSucceed.equals("true")) {
                    presenter.onActivatedSticker();
                } else {
                    presenter.showToast(baseMessage.message);
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
