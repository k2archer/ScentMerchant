package com.kwei.scentmerchant.presenter;

import com.kwei.scentmerchant.bean.StatisticsItem;
import com.kwei.scentmerchant.contract.StatisticsContract;
import com.kwei.scentmerchant.model.StatisticsModel;
import com.kwei.scentmerchant.model.bean.StatisticsData;

import java.util.List;

public class StatisticsPresenter implements StatisticsContract.Presenter {

    private StatisticsContract.View statisticsView;
    private StatisticsContract.Model statisticsModel;

    public StatisticsPresenter(StatisticsContract.View view) {
        statisticsView = view;
        statisticsModel = new StatisticsModel();
    }

    public void getStatistics() {
        statisticsModel.getStatistics(this);
    }

    public void showStatistics(StatisticsData statisticsData) {
        statisticsView.showStatistics(statisticsData);
    }

    @Override
    public void showToast(String message) {
        statisticsView.showToast(message);
    }

    @Override
    public void onFail(String message) {
        statisticsView.onFail(message);
    }

    @Override
    public void updateList(List<StatisticsItem> data) {
        statisticsView.updateList(data);
    }

    public void getStatisticsPage() {
        statisticsModel.getStatisticsPage(statisticsView.getStickerId(),this);
    }
}
