package com.kwei.scentmerchant.activity;

import android.os.Bundle;
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
import java.util.List;

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

    private String stickerId;
    private StatisticsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);

        stickerId = getIntent().getStringExtra("sticker_id");

        presenter = new StatisticsPresenter(this);
        presenter.getStatistics();

        init();
    }

    private void init() {
        statisticsList = new ArrayList<>();

        statisticsListView.setLayoutManager(new LinearLayoutManager(this));
        statisticsListAdapter = new StatisticsAdapter(this, statisticsList);
        statisticsListAdapter.setLoadMoreLayout(R.layout.default_loading);
        statisticsListAdapter.setOnLoadMoreListener(new StatisticsAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.getStatisticsPage();
            }
        });
        statisticsListView.setAdapter(statisticsListAdapter);

        presenter.getStatisticsPage();
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
    public void updateList(List<StatisticsItem> statisticsItemList) {
        statisticsListView.post(new Runnable() {
            @Override
            public void run() {
                statisticsList.addAll(statisticsItemList);
                statisticsListAdapter.notifyDataSetChanged();
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

    @Override
    public String getStickerId() {
        return stickerId;
    }
}
