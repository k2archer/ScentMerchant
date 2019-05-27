package com.kwei.scentmerchant.model;

import com.kwei.scentmerchant.contract.LoginContract;
import com.kwei.scentmerchant.model.bean.BaseMessage;
import com.kwei.scentmerchant.model.bean.LoginBody;
import com.kwei.scentmerchant.model.bean.RequestVerifyCodeBody;
import com.kwei.scentmerchant.model.retrofit.ExecptionUtils;
import com.kwei.scentmerchant.model.retrofit.RetrofitFactory;
import com.kwei.scentmerchant.utils.ToolUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModelImpl implements LoginContract.LoginModel {
    @Override
    public boolean getVerifyCode(String mobile, LoginContract.LoginPresenter presenter) {

        if (!ToolUtils.isMobileNumber(mobile)) {
            presenter.onFail("请输入正确手机号");
            return false;
        }

        requestSendVerifyCode(mobile, presenter);

        return true;
    }

    private void requestSendVerifyCode(String mobile, LoginContract.LoginPresenter presenter) {
        RequestVerifyCodeBody body = new RequestVerifyCodeBody(mobile);
        Observable<BaseMessage> observable = RetrofitFactory.getApiService().requestVerifyCode(body);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseMessage baseMessage) {
                        presenter.onFail(baseMessage.message);
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void login(String mobile, String verifyCode, LoginContract.LoginPresenter presenter) {

        if (!ToolUtils.isMobileNumber(mobile)) {
            presenter.onFail("请输入正确手机号");
            return;
        }
        if (!verifyVerifyCode(verifyCode)) {
            presenter.onFail("请输入正确验证码");
            return;
        }

        requestLogin(mobile, verifyCode, presenter);
    }

    private void requestLogin(String mobile, String verifyCode, LoginContract.LoginPresenter presenter) {
        LoginBody loginBody = new LoginBody(mobile, verifyCode);
        Observable<BaseMessage> observable = RetrofitFactory.getApiService().login(loginBody);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseMessage baseMessage) {
                        if (baseMessage.isSucceed.equals("true")) {
                            presenter.onSuccess();
                        } else {
                            presenter.showToast(baseMessage.message);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = ExecptionUtils.handleException(e);
                        presenter.onFail(message);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private boolean verifyVerifyCode(String code) {
        // todo 仅验证验证码有效性
        return code.length() == 6;
    }
}
