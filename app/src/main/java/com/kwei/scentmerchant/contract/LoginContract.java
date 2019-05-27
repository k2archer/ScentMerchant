package com.kwei.scentmerchant.contract;

public interface LoginContract {
    interface LoginModel {
        boolean getVerifyCode(String mobile, LoginPresenter presenter);

        void login(String mobile, String verifyCode, LoginPresenter presenter);
    }

    interface LoginView {
        String getMobile();

        String getVerifyCode();

        // contact presenter
        void onSuccess();

        void onFail(String message);

        void showToast(String message);
    }

    interface LoginPresenter {
        void onSuccess();

        void onFail(String message);

        void showToast(String message);
    }
}
