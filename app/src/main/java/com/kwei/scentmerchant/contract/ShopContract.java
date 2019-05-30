package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.bean.ShopBean;

public interface ShopContract {
    interface Model {
        void addShop(ShopBean shop, ShopPresenter shopPresenter);

    }

    interface View {

        String getMobile();

        String getShopName();

        String getShopAddress();

        String getShopFoodType();

        String getShopAverageConsumption();

        String getShopTableAmount();

        String[] getShopPictures();

        String getExclusiveMessage();


        // contact presenter
        void onSuccess();

        void onFail(String message);

        void showToast(String message);
    }

    interface ShopPresenter {

        void onAddNewShopSuccess();

        void onFail(String message);

        void showToast(String message);

        void addShop();
    }
}