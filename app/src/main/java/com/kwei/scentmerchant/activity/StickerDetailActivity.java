package com.kwei.scentmerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.StartActivity;
import com.kwei.scentmerchant.contract.StickerContract;
import com.kwei.scentmerchant.presenter.StickerPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StickerDetailActivity extends AppCompatActivity implements StickerContract.View {

    @BindView(R.id.tv_active_sticker)
    TextView tvActiveSticker;

    String stickerUrl;
    StickerContract.StickerPresenter stickerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_detail);
        ButterKnife.bind(this);

        stickerPresenter = new StickerPresenter(this);

        stickerUrl = getIntent().getStringExtra("sticker_url");
    }

    @OnClick(R.id.tv_active_sticker)
    public void onViewClicked() {
        stickerPresenter.activateSticker();
    }

    @Override
    public String getStickerUrl() {
        return stickerUrl;
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
}
