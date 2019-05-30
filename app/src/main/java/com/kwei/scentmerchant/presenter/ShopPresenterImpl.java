package com.kwei.scentmerchant.presenter;

import com.kwei.scentmerchant.bean.ShopBean;
import com.kwei.scentmerchant.contract.ShopContract;
import com.kwei.scentmerchant.model.ShopModelImpl;

import java.util.List;

public class ShopPresenterImpl implements ShopContract.ShopPresenter {

    private ShopContract.View shopView;
    private ShopContract.Model shopModel;

    public ShopPresenterImpl(ShopContract.View view) {
        shopView = view;
        shopModel = new ShopModelImpl();
    }

    @Override
    public void addShop() {

        ShopBean shop = new ShopBean();
        shop.mobile = shopView.getMobile();
        shop.name = shopView.getShopName();
        shop.address = shopView.getShopAddress();
        shop.averageConsumption = Integer.valueOf(shopView.getShopAverageConsumption());
        shop.foodType = shopView.getShopFoodType();
        shop.tableAmount = Integer.valueOf(shopView.getShopTableAmount());
        shop.pictures = shopView.getShopPictures();
        shop.exclusiveMessage = shopView.getExclusiveMessage();

        shopModel.addShop(shop, this);
    }

    @Override
    public void
    onAddNewShopSuccess() {
        shopView.onSuccess();
    }

    @Override
    public void onFail(String message) {
        shopView.onFail(message);
    }

    @Override
    public void showToast(String message) {
        shopView.showToast(message);
    }

}
