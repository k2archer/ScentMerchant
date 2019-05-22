package com.kwei.scentmerchant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.adapter.StatisticsAdapter;
import com.kwei.scentmerchant.bean.StatisticsItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticsActivity extends AppCompatActivity {

    @BindView(R.id.tv_back_sign)
    TextView tvBackSing;

    private RecyclerView statisticsListView;
    private StatisticsAdapter statisticsListAdapter;
    private ArrayList<StatisticsItem> statisticsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        statisticsListView = findViewById(R.id.rv_statistics_view);
        statisticsListView.setLayoutManager(new LinearLayoutManager(this));

        statisticsList = new ArrayList<>();
        StatisticsItem item = new StatisticsItem();

        // start 仅测试用，随后删除
        item.scanType = "微信扫码";
        item.scanDate = "2019-5-20";
        item.scanIncom = "¥ 2.08";
        item.advSharingType = "触发广告分成";
        statisticsList.add(item);
        // end

        statisticsListAdapter = new StatisticsAdapter(this, statisticsList);
        statisticsListView.setAdapter(statisticsListAdapter);
    }

    @OnClick(R.id.tv_back_sign)
    public void onViewClicked() {
        onBackPressed();
    }
}
