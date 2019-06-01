package com.kwei.scentmerchant.model;

import com.kwei.scentmerchant.contract.StickerIncomeContract;
import com.kwei.scentmerchant.model.bean.StickerResponseBody;
import com.kwei.scentmerchant.model.bean.base.BaseResponseBody;
import com.kwei.scentmerchant.model.retrofit.ExceptionUtils;
import com.kwei.scentmerchant.model.retrofit.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class StickerIncomeModel implements StickerIncomeContract.Model {
    @Override
    public void getSticker(String mobile, StickerIncomeContract.Presenter presenter) {
        Map<String, String> body = new HashMap<>();
        body.put("mobile", mobile);

        Observable<StickerResponseBody> observable;
        observable = RetrofitFactory.getApiService().getSticker(body);

        Observer<StickerResponseBody> observer = new Observer<StickerResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StickerResponseBody sticker) {
                if (sticker.code == BaseResponseBody.SUCCESS_CODE) {
                    presenter.showSticker(sticker.sticker);
                } else {
                    presenter.showToast(sticker.message);
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
