package com.kwei.scentmerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.contract.StickerIncomeContract;
import com.kwei.scentmerchant.model.bean.StickerDetail;
import com.kwei.scentmerchant.presenter.StickerIncomePresenter;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StickerIncomeActivity extends AppCompatActivity implements StickerIncomeContract.View {

    @BindView(R.id.tv_sticker_income)
    TextView tvStickerIncome;

    String mobile;
    StickerIncomePresenter presenter;
    @BindView(R.id.sticker_amount)
    TextView stickerAmount;
    @BindView(R.id.sticker_content)
    TextView stickerContent;
    @BindView(R.id.tv_sticker_activate_date)
    TextView tvStickerActivateDate;
    @BindView(R.id.tv_sticker_cycle)
    TextView tvStickerCycle;
    @BindView(R.id.iv_sticker_adv_bar)
    AppCompatImageView ivStickerAdvBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_income);
        ButterKnife.bind(this);

        mobile = getIntent().getStringExtra("mobile");

        presenter = new StickerIncomePresenter(this);
        presenter.getStickerDetail();
    }


    @OnClick(R.id.tv_sticker_income)
    public void onViewClicked() {
        startActivity(new Intent(this, StatisticsActivity.class));
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public void onFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSticker(StickerDetail sticker) {
        if (sticker.poster != null) {
            // todo ... set poster image
//            ivStickerAdvBar.setImageXXX();
        }
        stickerContent.setText(sticker.content);
        stickerAmount.setText(sticker.amount + "个");
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(sticker.date.getTime());
        tvStickerActivateDate.setText(time);
        tvStickerCycle.setText(sticker.cycle + "天");
    }
}
