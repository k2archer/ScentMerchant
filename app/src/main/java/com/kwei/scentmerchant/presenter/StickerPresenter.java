package com.kwei.scentmerchant.presenter;

import com.kwei.scentmerchant.contract.StickerContract;
import com.kwei.scentmerchant.model.StickerModelImpl;

public class StickerPresenter implements StickerContract.StickerPresenter {

    private StickerContract.View stickerView;
    private StickerContract.Model stickerModel;

    public StickerPresenter(StickerContract.View view) {
        stickerView = view;
        stickerModel = new StickerModelImpl();
    }

    @Override
    public void onFail(String message) {
        stickerView.onFail(message);
    }

    @Override
    public void showToast(String message) {
        stickerView.showToast(message);
    }

    @Override
    public void activateSticker() {
        stickerModel.activateSticker(stickerView.getStickerUrl(), this);
    }

    @Override
    public void onActivatedSticker() {
        stickerView.onActivatedSticker();
    }
}
