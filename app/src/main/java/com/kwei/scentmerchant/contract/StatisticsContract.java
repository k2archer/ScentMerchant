package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.model.bean.StatisticsData;

public interface StatisticsContract {
    interface Model {

        void getStatistics(Presenter presenter);
    }

    interface View {

        void showStatistics(StatisticsData statisticsData);

        void showToast(String message);

        void onFail(String message);
    }

    interface Presenter {

        void showStatistics(StatisticsData statisticsData);

        void showToast(String message);

        void onFail(String message);
    }
}
