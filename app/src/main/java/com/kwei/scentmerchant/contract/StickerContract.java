package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.model.bean.StickerDetail;

public interface StickerContract {
    interface Model {
        void getStickerDetail(String url, StickerPresenter presenter);

        void activateSticker(String id, StickerContract.StickerPresenter presenter);
    }

    interface View {
        String getStickerUrl();

        void showStickerDetail(StickerDetail sticker);

        void onActivatedSticker();

        void onFail(String message);

        void showToast(String message);

        String getStickerId();
    }

    interface StickerPresenter {

        void onFail(String message);

        void showToast(String message);

        void getStickerDetail(String url);

        void showStickerDetail(StickerDetail sticker);

        void activateSticker();

        void onActivatedSticker();
    }
}
