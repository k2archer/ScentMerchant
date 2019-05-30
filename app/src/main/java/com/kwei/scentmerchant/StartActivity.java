package com.kwei.scentmerchant;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kwei.scentmerchant.activity.ShopActivity;
import com.kwei.scentmerchant.activity.StickerDetailActivity;
import com.kwei.scentmerchant.bean.ShopBean;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.tv_more_info)
    TextView tvMoreInfo;
    @BindView(R.id.tv_add_merchant)
    TextView tvAddMerchant;
    @BindView(R.id.tv_active_desk)
    TextView tvActiveDesk;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;

    ShopBean shop;
    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        if (shop == null) {
            tvActiveDesk.setEnabled(false);
            tvStart.setEnabled(false);
        }

        mobile = getIntent().getStringExtra("login_mobile");
        String mobileNumber = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
        tvMobile.setText(mobileNumber);
    }

    @OnClick({R.id.tv_add_merchant, R.id.tv_active_desk, R.id.tv_start, R.id.bt_start, R.id.tv_more_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add_merchant:
                Intent intent = new Intent(this, ShopActivity.class);
                startActivityForResult(intent, ADD_SHOP_REQUEST_CODE);
                break;
            case R.id.tv_active_desk:
                activeSticker();
                break;
            case R.id.tv_start:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bt_start:
                break;
            case R.id.tv_more_info:
                break;
        }
    }

    private void activeSticker() {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Intent intent = new Intent(StartActivity.this, CaptureActivity.class);
            intent.putExtra("login_mobile", mobile);
            startActivityForResult(intent, SCAN_REQUEST_CODE);
        } else {
            Toast.makeText(this, "相机不支持", Toast.LENGTH_SHORT).show();
        }
    }


    static public final int ADD_SHOP_REQUEST_CODE = 1001;
    static public final int SCAN_REQUEST_CODE = 1002;
    static public final int ACTIVE_STICKER_CODE = 1003;
    public final String RESULT_CODE = "add_merchant";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_SHOP_REQUEST_CODE && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                tvActiveDesk.setEnabled(true);
                TextView tv_1 = findViewById(R.id.circle_1);
                Drawable originalDrawable = ContextCompat.getDrawable(this, R.drawable.circle_style);
                Drawable tintDrawable = DrawableCompat.wrap(originalDrawable).mutate();
                DrawableCompat.setTint(tintDrawable, Color.parseColor("#FDB700"));
                tv_1.setBackground(tintDrawable);
            }
        }

        /** 处理二维码扫描结果 **/
        if (requestCode == SCAN_REQUEST_CODE && null != data) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    tvStart.setEnabled(true);
                    // 显示广告贴纸信息
                    startActivity(new Intent(this, StickerDetailActivity.class));
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        if (requestCode == ACTIVE_STICKER_CODE && data != null) {
            TextView tv_2 = findViewById(R.id.circle_2);
            Drawable originalDrawable = ContextCompat.getDrawable(this, R.drawable.circle_style);
            Drawable tintDrawable = DrawableCompat.wrap(originalDrawable).mutate();
            DrawableCompat.setTint(tintDrawable, Color.parseColor("#FDB700"));
            tv_2.setBackground(tintDrawable);
        }
    }
}
