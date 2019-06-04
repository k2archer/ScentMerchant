package com.kwei.scentmerchant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.adapter.NotificationAdapter;
import com.kwei.scentmerchant.bean.NotificationItem;
import com.kwei.scentmerchant.contract.NotificationContract;
import com.kwei.scentmerchant.presenter.NotificationPresenter;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity implements NotificationContract.View {

    private RecyclerView notificationListView;
    private NotificationAdapter notificationListAdapter;
    private ArrayList<NotificationItem> notificationList;

    private NotificationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        presenter = new NotificationPresenter(this);

        init();
    }

    private void init() {
        notificationListView = findViewById(R.id.rv_notification_list);
        notificationListView.setLayoutManager(new LinearLayoutManager(this));

        notificationList = new ArrayList<>();

        notificationListAdapter = new NotificationAdapter(this, notificationList);
        notificationListAdapter.setLoadMoreLayout(R.layout.default_loading);
        notificationListAdapter.setOnLoadMoreListener(new NotificationAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.getNotificationList();
            }
        });
        notificationListView.setAdapter(notificationListAdapter);

        presenter.getNotificationList();
    }

    @Override
    public void updateList(List<NotificationItem> list) {
        notificationListView.post(new Runnable() {
            @Override
            public void run() {
                notificationList.addAll(list);
                notificationListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
