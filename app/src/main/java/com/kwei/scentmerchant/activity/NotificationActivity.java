package com.kwei.scentmerchant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.adapter.NotificationAdapter;
import com.kwei.scentmerchant.bean.NotificationItem;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView notificationListView;
    private NotificationAdapter notificationListAdapter;
    private ArrayList<NotificationItem> notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        init();
    }

    private void init() {
        notificationListView = findViewById(R.id.rv_notification_list);
        notificationListView.setLayoutManager(new LinearLayoutManager(this));

        notificationList = new ArrayList<>();
        NotificationItem item = new NotificationItem();

        // start 仅测试用，随后删除
        item.date = "2019年05月19日 09:13";
        item.title = "店铺审核通过";
        item.message = "恭喜你，你提交“蜜小屋(万达广场店)”现已通过审核，相关权限和功能已开通。";
        notificationList.add(item);
        // end

        notificationListAdapter = new NotificationAdapter(this, notificationList);
        notificationListView.setAdapter(notificationListAdapter);
    }
}
