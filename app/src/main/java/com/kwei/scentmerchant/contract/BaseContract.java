package com.kwei.scentmerchant.contract;

public interface BaseContract {

    interface View {
        void onFail(String message);

        void showToast(String message);

    }

    interface Presenter {
        void onFail(String message);

        void showToast(String message);
    }
}
