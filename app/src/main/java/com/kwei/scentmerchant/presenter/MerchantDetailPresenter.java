package com.kwei.scentmerchant.presenter;

import com.kwei.scentmerchant.bean.MerchantDetail;
import com.kwei.scentmerchant.contract.MerchantDetailContract;
import com.kwei.scentmerchant.model.MerchantDetailModel;

public class MerchantDetailPresenter implements MerchantDetailContract.Presenter {

    private MerchantDetailContract.View view;
    private MerchantDetailContract.Model model;


    public MerchantDetailPresenter(MerchantDetailContract.View view) {
        this.view = view;
        model = new MerchantDetailModel();
    }

    @Override
    public void getMerchantDetail() {
        model.getMerchantDetail(this);
    }

    @Override
    public void showMerchantDetail(MerchantDetail merchant) {
        view.showMerchantDetail(merchant);
    }

    @Override
    public void summitVerify() {
        model.submitVerify(this);
    }

    @Override
    public void onFail(String message) {
        view.onFail(message);
    }

    @Override
    public void showToast(String message) {
        view.showToast(message);
    }

    @Override
    public void onSubmitSucceed() {
        view.onSubmitSucceed();
    }
}
