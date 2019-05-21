package com.kwei.scentmerchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StickerDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_active_sticker)
    TextView tvActiveSticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_detail);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_active_sticker)
    public void onViewClicked() {
        onBackPressed();
    }
}
