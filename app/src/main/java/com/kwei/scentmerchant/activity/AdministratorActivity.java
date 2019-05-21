package com.kwei.scentmerchant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kwei.scentmerchant.AdminItem;
import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.bean.SetAdminEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class AdministratorActivity extends AppCompatActivity {

    @BindView(R.id.tv_add_admin)
    TextView tvAddAdmin;
    @BindView(R.id.tv_support_admin_name)
    TextView tvSupportAdminName;

    private RecyclerView adminListView;
    private AdminListAdapter adminListAdapter;
    private ArrayList<AdminItem> adminList;


    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onMessageEvent(SetAdminEvent event) {
        tvSupportAdminName.setText(event.getName());
    }

    @Subscribe
    public void handleSomethingElse(SetAdminEvent event) {
        ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        adminListView = findViewById(R.id.rv_admin_list);
        adminListView.setLayoutManager(new LinearLayoutManager(this));

        adminList = new ArrayList<>();
        AdminItem admin = new AdminItem();

        // start 仅测试用，随后删除
        admin.name = "陈*葭";
        admin.phone = "1883****1035";
        adminList.add(admin);
        // end

        adminListAdapter = new AdminListAdapter(this, adminList);

        adminListView.setAdapter(adminListAdapter);
    }

    @OnClick(R.id.tv_add_admin)
    public void onAddAdminClicked() {
        // todo add a dialog to add a adminItem
        AdminItem admin = new AdminItem();
        admin.name = admin.toString();
        adminListAdapter.addItem(admin);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
