package com.kwei.scentmerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.StartActivity;

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
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        setResult(StartActivity.ACTIVE_STICKER_CODE, intent);
        finish();
    }
}
