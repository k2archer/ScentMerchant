package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.model.bean.StickerDetail;

public interface StickerIncomeContract {
    interface Model {
        void getSticker(String mobile, StickerIncomeContract.Presenter presenter);
    }

    interface View extends BaseContract.View {
        String getMobile();

        void showSticker(StickerDetail sticker);
    }

    interface Presenter extends BaseContract.Presenter {
        void showSticker(StickerDetail sticker);
    }

}
