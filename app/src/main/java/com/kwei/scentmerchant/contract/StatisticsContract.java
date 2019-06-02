package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.bean.StatisticsItem;
import com.kwei.scentmerchant.model.bean.StatisticsData;

import java.util.List;

public interface StatisticsContract {
    interface Model {

        void getStatistics(Presenter presenter);

        void getStatisticsPage(String stickerId, StatisticsContract.Presenter presenter);
    }

    interface View {

        void showStatistics(StatisticsData statisticsData);

        void updateList(List<StatisticsItem> statisticsItemList);

        void showToast(String message);

        void onFail(String message);

        String getStickerId();
    }

    interface Presenter {

        void showStatistics(StatisticsData statisticsData);

        void showToast(String message);

        void onFail(String message);

        void updateList(List<StatisticsItem> data);
    }
}
