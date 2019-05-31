package com.kwei.scentmerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.StartActivity;
import com.kwei.scentmerchant.contract.StickerContract;
import com.kwei.scentmerchant.model.bean.StickerDetail;
import com.kwei.scentmerchant.presenter.StickerPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StickerDetailActivity extends AppCompatActivity implements StickerContract.View {

    @BindView(R.id.tv_active_sticker)
    TextView tvActiveSticker;

    String stickerUrl;
    StickerContract.StickerPresenter stickerPresenter;
    @BindView(R.id.sticker_content)
    TextView stickerContent;
    @BindView(R.id.sticker_amount)
    TextView stickerAmount;
    @BindView(R.id.sticker_cycle)
    TextView stickerCycle;
    @BindView(R.id.sticker_income_model)
    TextView stickerIncomeModel;
    @BindView(R.id.tv_income_settlement)
    TextView tvIncomeSettlement;
    @BindView(R.id.tv_scan_code)
    TextView tvScanCode;

    StickerDetail sticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_detail);
        ButterKnife.bind(this);

        stickerPresenter = new StickerPresenter(this);

        stickerUrl = getIntent().getStringExtra("sticker_url");
        stickerPresenter.getStickerDetail(stickerUrl);
    }

    @OnClick(R.id.tv_active_sticker)
    public void onViewClicked() {
        if (sticker != null) {
            if (sticker.date == null) {
                stickerPresenter.activateSticker();
            }
        }
    }

    @Override
    public String getStickerUrl() {
        return stickerUrl;
    }

    @Override
    public void showStickerDetail(StickerDetail sticker) {
        if (sticker != null) {
            stickerContent.setText(sticker.content);
            stickerAmount.setText(String.valueOf(sticker.amount) + " 个");
            stickerCycle.setText(String.valueOf(sticker.cycle) + " 天");
            stickerIncomeModel.setText(sticker.model);
            tvIncomeSettlement.setText(sticker.settlement);
            tvScanCode.setText(sticker.scanMode);

            this.sticker = sticker;
        } else {
            stickerContent.setText("");
            stickerAmount.setText("0 个");
            stickerCycle.setText("0 天");
            stickerIncomeModel.setText("");
            tvIncomeSettlement.setText("");
            tvScanCode.setText("");
        }
    }

    @Override
    public void onActivatedSticker() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        setResult(StartActivity.ACTIVE_STICKER_CODE, intent);
        finish();
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
    public String getStickerId() {
        return sticker.id;
    }
}
