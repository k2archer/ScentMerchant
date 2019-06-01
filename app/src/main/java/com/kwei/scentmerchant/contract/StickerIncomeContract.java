package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.model.bean.StickerDetail;

public interface StickerIncomeContract {
    interface Model {
        void getSticker(String mobile, StickerIncomeContract.Presenter presenter);
    }

    interface View {
        String getMobile();

        void onFail(String message);

        void showToast(String message);

        void showSticker(StickerDetail sticker);
    }

    interface Presenter {
        void onFail(String message);

        void showToast(String message);

        void showSticker(StickerDetail sticker);
    }

}
