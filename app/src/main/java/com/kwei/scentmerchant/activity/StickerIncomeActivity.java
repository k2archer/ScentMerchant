package com.kwei.scentmerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kwei.scentmerchant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StickerIncomeActivity extends AppCompatActivity {

    @BindView(R.id.tv_sticker_income)
    TextView tvStickerIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_income);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_sticker_income)
    public void onViewClicked() {
        startActivity(new Intent(this, StatisticsActivity.class));
    }
}
