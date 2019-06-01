package com.kwei.scentmerchant.presenter;

import com.kwei.scentmerchant.contract.StickerIncomeContract;
import com.kwei.scentmerchant.model.StickerIncomeModel;
import com.kwei.scentmerchant.model.bean.StickerDetail;

public class StickerIncomePresenter implements StickerIncomeContract.Presenter {

    StickerIncomeContract.View stickerIncomeView;
    StickerIncomeModel stickerIncomeModel;

    public StickerIncomePresenter(StickerIncomeContract.View view) {
        this.stickerIncomeView = view;
        stickerIncomeModel = new StickerIncomeModel();
    }


    public void  getStickerDetail() {
        stickerIncomeModel.getSticker(stickerIncomeView.getMobile(), this);
    }

    @Override
    public void onFail(String message) {
        stickerIncomeView.onFail(message);
    }

    @Override
    public void showToast(String message) {
        stickerIncomeView.showToast(message);
    }

    @Override
    public void showSticker(StickerDetail sticker) {
        stickerIncomeView.showSticker(sticker);
    }
}
