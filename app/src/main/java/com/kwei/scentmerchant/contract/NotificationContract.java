package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.bean.NotificationItem;
import com.kwei.scentmerchant.presenter.NotificationPresenter;

import java.util.List;

public interface NotificationContract {
    interface Model {

        void getNotificationList(NotificationPresenter presenter);
    }

    interface View extends BaseContract.View {
        void updateList(List<NotificationItem> list);
    }

    interface Presenter extends BaseContract.Presenter {
        void getNotificationList();

        void updateList(List<NotificationItem> list);
    }
}
