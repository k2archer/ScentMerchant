package com.kwei.scentmerchant.model;

import android.util.Log;

import com.google.gson.Gson;
import com.kwei.scentmerchant.contract.StickerContract;
import com.kwei.scentmerchant.model.bean.StickerResponseBody;
import com.kwei.scentmerchant.model.bean.StickerRequestBody;
import com.kwei.scentmerchant.model.bean.base.BaseRequestBody;
import com.kwei.scentmerchant.model.bean.base.BaseResponseBody;
import com.kwei.scentmerchant.model.retrofit.ExceptionUtils;
import com.kwei.scentmerchant.model.retrofit.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class StickerModelImpl implements StickerContract.Model {

    private String parseID(String url) {
        String id = null;
        if (url.matches("^((https|http|ftp|rtsp|mms)?:\\/\\/)([^/]*/)*sticker\\?id=[\\w]*")) {
            String[] match = url.split("\\?", 2);
            if (match.length == 2) {
                String[] split = match[1].split("=", 2);
                if (split[0].equals("id")) {
                    id = split[1];
                }
            }
        }
        return id;
    }

    @Override
    public void getStickerDetail(String url, StickerContract.StickerPresenter presenter) {
        String id = parseID(url);
        if (id != null) {
            StickerRequestBody requestBody = new StickerRequestBody();
            requestBody.id = id;
            requestBody.token = "token1";
            Gson gson = new Gson();
            Observable<StickerResponseBody> observable;
            observable = RetrofitFactory.getApiService().getStickerDetail(gson.toJson(requestBody));

            Observer<StickerResponseBody> observer = new Observer<StickerResponseBody>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(StickerResponseBody sticker) {
                    if (sticker.code == BaseResponseBody.SUCCESS_CODE) {
                        presenter.showStickerDetail(sticker.sticker);
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

            requestStickerDetail(url, presenter);
        } else {
            presenter.showToast("二维码错误");
        }
    }


    public void requestStickerDetail(String url, StickerContract.StickerPresenter presenter) {
        Log.d("TAG", "getStickerDetail: " + url);
        BaseRequestBody requestBody = new BaseRequestBody();
        requestBody.token = "token1";
        Gson gson = new Gson();
        Observable<StickerResponseBody> observable;
        observable = RetrofitFactory.getApiService().getStickerDetail(gson.toJson(requestBody));

        Observer<StickerResponseBody> observer = new Observer<StickerResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StickerResponseBody sticker) {
                if (sticker.code == BaseResponseBody.SUCCESS_CODE) {
                    presenter.showStickerDetail(sticker.sticker);
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

    @Override
    public void activateSticker(String id, StickerContract.StickerPresenter presenter) {
        if (id != null) {
            StickerRequestBody requestBody = new StickerRequestBody();
            requestBody.id = id;
            requestBody.token = "token1";
            Gson gson = new Gson();
            String body = gson.toJson(requestBody);
            Observable<BaseResponseBody> observable;
            observable = RetrofitFactory.getApiService().activateSticker(body);

            Observer<BaseResponseBody> observer = new Observer<BaseResponseBody>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(BaseResponseBody responseBody) {
                    if (responseBody.code == BaseResponseBody.SUCCESS_CODE) {
                        presenter.onActivatedSticker();
                    } else {
                        presenter.showToast(responseBody.message);
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
        } else {
            presenter.showToast("Sticker ID 错误");
        }
    }
}
