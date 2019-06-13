package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.bean.MerchantDetail;

public interface MerchantDetailContract {
    interface Model {
        void getMerchantDetail(Presenter presenter);

        void submitVerify(Presenter presenter);
    }

    interface View extends BaseContract.View {
        void showMerchantDetail(MerchantDetail merchant);

        void onSubmitSucceed();
    }

    interface Presenter extends BaseContract.Presenter {
        void getMerchantDetail();

        void showMerchantDetail(MerchantDetail merchant);

        void summitVerify();

        void onSubmitSucceed();
    }
}
