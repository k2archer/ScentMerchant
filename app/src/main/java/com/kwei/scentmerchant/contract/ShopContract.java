package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.bean.ShopBean;

public interface ShopContract {
    interface Model {
        void addShop(ShopBean shop, ShopPresenter shopPresenter);

    }

    interface View extends BaseContract.View {

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
    }

    interface ShopPresenter extends BaseContract.Presenter {

        void onAddNewShopSuccess();

        void addShop();

        void getShopDetail();
    }
}