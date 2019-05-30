package com.kwei.scentmerchant.model;

import com.google.gson.Gson;
import com.kwei.scentmerchant.bean.ShopBean;
import com.kwei.scentmerchant.contract.ShopContract;
import com.kwei.scentmerchant.model.bean.BaseMessage;
import com.kwei.scentmerchant.model.retrofit.ExceptionUtils;
import com.kwei.scentmerchant.model.retrofit.RetrofitFactory;

import java.io.File;
import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ShopModelImpl implements ShopContract.Model {
    @Override
    public void addShop(ShopBean shop, ShopContract.ShopPresenter presenter) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        // 多张图片
        for (int i = 0; i < shop.pictures.length; i++) {
            File file = new File(shop.pictures[i]); // filePath 图片地址
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            String uploadedName = UUID.randomUUID().toString();
            builder.addFormDataPart(file.getName(), uploadedName, imageBody);
            shop.pictures[i] = uploadedName;
        }

        String body = new Gson().toJson(shop);
        builder.setType(MultipartBody.FORM)
                .addFormDataPart("body", body);

        List<MultipartBody.Part> parts = builder.build().parts();

        Observable<BaseMessage> observable = RetrofitFactory.getApiService().addNewShop(parts);

        Observer<BaseMessage> observer = new Observer<BaseMessage>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseMessage baseMessage) {
                if (baseMessage.isSucceed.equals("true")) {
                    presenter.onAddNewShopSuccess();
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

        subscribe(observable, observer);
    }

    private void subscribe(Observable<BaseMessage> observable, Observer<BaseMessage> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
