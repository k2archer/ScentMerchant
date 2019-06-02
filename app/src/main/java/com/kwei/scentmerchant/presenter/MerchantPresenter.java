package com.kwei.scentmerchant.presenter;

import com.kwei.scentmerchant.bean.MerchantAccount;
import com.kwei.scentmerchant.bean.SettlementItem;
import com.kwei.scentmerchant.contract.MerchantContract;
import com.kwei.scentmerchant.model.MerchantModel;

import java.util.List;

public class MerchantPresenter implements MerchantContract.Presenter {

    MerchantContract.View merchantView;
    MerchantContract.Model merchantModel;

    public MerchantPresenter(MerchantContract.View view) {
        merchantView = view;
        merchantModel = new MerchantModel();
    }

    @Override
    public void getMerchantAccount() {
        merchantModel.getMerchantAccount(this);
    }

    @Override
    public void showAccount(MerchantAccount account) {
        merchantView.showAccount(account);
    }

    @Override
    public void getSettlementList() {
        merchantModel.getSettlementList(this);
    }

    @Override
    public void updateList(List<SettlementItem> list) {
        merchantView.updateList(list);
    }

    @Override
    public void onFail(String message) {
        merchantView.onFail(message);
    }

    @Override
    public void showToast(String message) {
        merchantView.showToast(message);
    }

}
