package com.kwei.scentmerchant.contract;

import com.kwei.scentmerchant.bean.MerchantAccount;
import com.kwei.scentmerchant.bean.SettlementItem;

import java.util.List;

public interface MerchantContract {
    interface Model {
        void getMerchantAccount(MerchantContract.Presenter presenter);

        void getSettlementList(Presenter presenter);
    }

    interface View extends BaseContract.View {
        void showAccount(MerchantAccount account);

        void updateList(List<SettlementItem> list);
    }

    interface Presenter extends BaseContract.Presenter {
        void getMerchantAccount();

        void showAccount(MerchantAccount account);

        void getSettlementList();

        void updateList(List<SettlementItem> list);

    }
}
