package com.kwei.scentmerchant.presenter;

import com.kwei.scentmerchant.contract.LoginContract;
import com.kwei.scentmerchant.model.LoginModelImpl;

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private LoginContract.LoginView loginView;
    private LoginContract.LoginModel loginModel;

    public LoginPresenterImpl(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }

    public boolean getVerifyCode() {
        String mobile = loginView.getMobile();
        return loginModel.getVerifyCode(mobile, this);
    }

    public void login() {
        String mobile = loginView.getMobile();
        String verifyCode = loginView.getVerifyCode();
        loginModel.login(mobile, verifyCode, this);
    }

    @Override
    public void onSuccess() {
        loginView.onSuccess();
    }

    @Override
    public void onFail(String message) {
        loginView.onFail(message);
    }
}
