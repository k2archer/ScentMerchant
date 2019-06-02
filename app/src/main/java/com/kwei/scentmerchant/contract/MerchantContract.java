package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.bean.MerchantAccount;

public interface MerchantContract {
    interface Model {
        public void getMerchantAccount(MerchantContract.Presenter presenter);
    }

    interface View {
        public void showAccount(MerchantAccount account);

        void onFail(String message);

        void showToast(String message);
    }

    interface Presenter {
        public void showAccount(MerchantAccount account);

        void onFail(String message);

        void showToast(String message);

        void getMerchantAccount();
    }
}
