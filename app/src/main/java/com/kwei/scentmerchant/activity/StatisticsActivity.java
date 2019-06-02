package com.kwei.scentmerchant.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.adapter.StatisticsAdapter;
import com.kwei.scentmerchant.bean.StatisticsItem;
import com.kwei.scentmerchant.contract.StatisticsContract;
import com.kwei.scentmerchant.model.bean.StatisticsData;
import com.kwei.scentmerchant.presenter.StatisticsPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticsActivity extends AppCompatActivity implements StatisticsContract.View {

    @BindView(R.id.tv_back_sign)
    TextView tvBackSing;
    @BindView(R.id.tv_pending_settlement)
    TextView tvPendingSettlement;
    @BindView(R.id.tv_cumulative_income)
    TextView tvCumulativeIncome;
    @BindView(R.id.rv_statistics_view)
    RecyclerView statisticsListView;

    private StatisticsAdapter statisticsListAdapter;
    private ArrayList<StatisticsItem> statisticsList;

    private StatisticsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);

        init();

        presenter = new StatisticsPresenter(this);
        presenter.getStatistics();
    }

    private void init() {
        statisticsList = new ArrayList<>();
        StatisticsItem item = new StatisticsItem();

        // start 仅测试用，随后删除
        item.scanType = "微信扫码";
        item.scanDate = "2019-5-20";
        item.scanIncome = "" + (int) (Math.random() * 26 + 97);
        item.advSharingType = "触发广告分成";
        statisticsList.add(item);
        // end

        statisticsListView.setLayoutManager(new LinearLayoutManager(this));
        statisticsListAdapter = new StatisticsAdapter(this, statisticsList);
        statisticsListAdapter.setLoadMoreLayout(R.layout.default_loading);
        statisticsListAdapter.setOnLoadMoreListener(new StatisticsAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        StatisticsItem item = new StatisticsItem();
                        item.scanType = "微信扫码";
                        item.scanDate = "2019-5-20";
                        item.scanIncome = "" + (int) (Math.random() * 26 + 97);
                        item.advSharingType = "触发广告分成";
                        statisticsList.add(item);
                        statisticsListAdapter.notifyDataSetChanged();
                    }
                }, 500);
            }
        });
        statisticsListView.setAdapter(statisticsListAdapter);
    }

    @OnClick(R.id.tv_back_sign)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void showStatistics(StatisticsData statisticsData) {
        tvPendingSettlement.setText(String.valueOf(statisticsData.pendingSettlement));
        tvCumulativeIncome.setText(String.valueOf(statisticsData.cumulativeIncome));
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
