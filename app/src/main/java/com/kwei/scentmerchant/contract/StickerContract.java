package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.model.bean.StickerDetail;

public interface StickerContract {
    interface Model {
        void getStickerDetail(String url, StickerPresenter presenter);

        void activateSticker(String id, StickerContract.StickerPresenter presenter);
    }

    interface View extends BaseContract.View {
        String getStickerUrl();

        void showStickerDetail(StickerDetail sticker);

        void onActivatedSticker();

        String getStickerId();
    }

    interface StickerPresenter extends BaseContract.Presenter {
        void getStickerDetail(String url);

        void showStickerDetail(StickerDetail sticker);

        void activateSticker();

        void onActivatedSticker();
    }
}
