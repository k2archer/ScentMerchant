package com.kwei.scentmerchant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.bean.StatisticsItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IncomeDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_merchant_id)
    TextView tvMerchantId;
    @BindView(R.id.tv_income)
    TextView tvIncome;
    @BindView(R.id.tv_scan_type)
    TextView tvScanType;
    @BindView(R.id.tv_income_type)
    TextView tvIncomeType;
    @BindView(R.id.tv_scan_date)
    TextView tvScanDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail);

        ButterKnife.bind(this);

        StatisticsItem item = (StatisticsItem) getIntent().getSerializableExtra("record");
        tvIncome.setText(item.scanIncome + '.');
        tvMerchantId.setText(item.merchantId);
        tvScanType.setText(item.scanType);
        tvIncomeType.setText(item.advSharingType);
        tvScanDate.setText(item.scanDate);
    }
}
