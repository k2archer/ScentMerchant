package com.kwei.scentmerchant.contract;

public interface LoginContract {
    interface LoginModel {
        boolean getVerifyCode(String phone, LoginPresenter presenter);
        void login(String phone, String verifyCode, LoginPresenter presenter);
    }

    interface LoginView {
        String getPhone();

        String getVerifyCode();

        void onSuccess();

        void onFail(String message);
    }

    interface LoginPresenter {
        void onSuccess();

        void onFail(String message);
    }
}
