package com.kwei.scentmerchant.contract;

public interface StickerContract {
    interface Model {
        void getStickerDetail(String StickerId, StickerPresenter presenter);

        void activateSticker(String url, StickerContract.StickerPresenter presenter);
    }

    interface View {
        String getStickerUrl();

        void onActivatedSticker();

        void onFail(String message);

        void showToast(String message);
    }

    interface StickerPresenter {

        void onFail(String message);

        void showToast(String message);

        void activateSticker();

        void onActivatedSticker();
    }
}
