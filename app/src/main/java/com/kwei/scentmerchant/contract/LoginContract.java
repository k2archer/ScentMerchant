package com.kwei.scentmerchant.contract;

public interface LoginContract {
    interface LoginModel {
        boolean getVerifyCode(String mobile, LoginPresenter presenter);

        void login(String mobile, String verifyCode, LoginPresenter presenter);
    }

    interface LoginView extends BaseContract.View {
        String getMobile();

        String getVerifyCode();

        void onSuccess();

        void newMerchant();
    }

    interface LoginPresenter extends BaseContract.Presenter{
        void onSuccess();

        void newMerchant();
    }
}
