package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.bean.NotificationItem;
import com.kwei.scentmerchant.presenter.NotificationPresenter;

import java.util.List;

public interface NotificationContract {
    interface Model {

        void getNotificationList(NotificationPresenter presenter);
    }

    interface View {
        void updateList(List<NotificationItem> list);

        void showToast(String message);

        void onFail(String message);
    }

    interface Presenter {
        void getNotificationList();

        void showToast(String message);

        void onFail(String message);

        void updateList(List<NotificationItem> list);
    }
}
