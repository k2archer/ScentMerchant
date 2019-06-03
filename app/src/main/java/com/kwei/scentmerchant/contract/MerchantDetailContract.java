package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.bean.MerchantDetail;

public interface MerchantDetailContract {
    interface Model {
        void getMerchantDetail(Presenter presenter);

        void submitVerify(Presenter presenter);
    }

    interface View {
        void showMerchantDetail(MerchantDetail merchant);

        void onFail(String message);

        void showToast(String message);

        void onSubmitSucceed();
    }

    interface Presenter {
        void getMerchantDetail();

        void showMerchantDetail(MerchantDetail merchant);

        void summitVerify();

        void onFail(String message);

        void showToast(String message);

        void onSubmitSucceed();
    }
}
