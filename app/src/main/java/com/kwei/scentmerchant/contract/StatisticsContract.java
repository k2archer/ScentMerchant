package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.bean.StatisticsItem;
import com.kwei.scentmerchant.model.bean.StatisticsData;

import java.util.List;

public interface StatisticsContract {
    interface Model {

        void getStatistics(Presenter presenter);

        void getStatisticsPage(String stickerId, StatisticsContract.Presenter presenter);
    }

    interface View extends BaseContract.View {

        void showStatistics(StatisticsData statisticsData);

        void updateList(List<StatisticsItem> statisticsItemList);

        String getStickerId();
    }

    interface Presenter extends BaseContract.Presenter {

        void showStatistics(StatisticsData statisticsData);

        void updateList(List<StatisticsItem> data);
    }
}
