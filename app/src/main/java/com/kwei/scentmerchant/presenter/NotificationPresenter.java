package com.kwei.scentmerchant.presenter;

import com.kwei.scentmerchant.bean.NotificationItem;
import com.kwei.scentmerchant.contract.NotificationContract;
import com.kwei.scentmerchant.model.NotificationModel;

import java.util.List;

public class NotificationPresenter implements NotificationContract.Presenter {

    private NotificationContract.View view;
    private NotificationContract.Model model;

    public NotificationPresenter(NotificationContract.View view) {
        this.view = view;
        model = new NotificationModel();
    }

    @Override
    public void getNotificationList() {
        model.getNotificationList(this);
    }

    @Override
    public void showToast(String message) {
        view.showToast(message);
    }

    @Override
    public void onFail(String message) {
        view.onFail(message);
    }

    @Override
    public void updateList(List<NotificationItem> list) {
        view.updateList(list);
    }
}
