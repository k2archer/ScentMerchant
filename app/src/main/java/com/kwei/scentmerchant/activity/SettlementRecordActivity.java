package com.kwei.scentmerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.adapter.SettlementAdapter;
import com.kwei.scentmerchant.bean.SettlementItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettlementRecordActivity extends AppCompatActivity {

    @BindView(R.id.tv_back_sign)
    TextView tvBackSign;
    @BindView(R.id.tv_enterprise_account_detail)
    TextView tvEnterpriseAccountDetail;

    private RecyclerView settlementListView;
    private SettlementAdapter settlementListAdapter;
    private ArrayList<SettlementItem> settlementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement_record);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        settlementListView = findViewById(R.id.rv_settlement_record);
        settlementListView.setLayoutManager(new LinearLayoutManager(this));

        settlementList = new ArrayList<>();
        SettlementItem item = new SettlementItem();

        // start 仅测试用，随后删除
        item.date = "2019年05月19日 09:13";
        item.amount = "¥ 2535.08";
        item.message = "收款1笔，已结算";
        settlementList.add(item);
        // end

        settlementListAdapter = new SettlementAdapter(this, settlementList);
        settlementListView.setAdapter(settlementListAdapter);
    }

    @OnClick({R.id.tv_back_sign, R.id.tv_enterprise_account_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back_sign:
                onBackPressed();
                break;
            case R.id.tv_enterprise_account_detail:
                startActivity(new Intent(this, EnterpriseAccountDetailActivity.class));
                break;
        }
    }
}
