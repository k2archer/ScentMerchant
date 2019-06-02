package com.kwei.scentmerchant.presenter;

import com.kwei.scentmerchant.bean.MerchantAccount;
import com.kwei.scentmerchant.contract.MerchantContract;
import com.kwei.scentmerchant.model.MerchantModel;

public class MerchantPresenter implements MerchantContract.Presenter {

    MerchantContract.View merchantView;
    MerchantContract.Model merchantModel;

    public MerchantPresenter(MerchantContract.View view) {
        merchantView = view;
        merchantModel = new MerchantModel();
    }

    @Override
    public void showAccount(MerchantAccount account) {
        merchantView.showAccount(account);
    }

    @Override
    public void onFail(String message) {
        merchantView.onFail(message);
    }

    @Override
    public void showToast(String message) {
        merchantView.showToast(message);
    }

    @Override
    public void getMerchantAccount() {
        merchantModel.getMerchantAccount(this);
    }
}
