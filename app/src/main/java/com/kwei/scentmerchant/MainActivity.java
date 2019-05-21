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
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_scan)
    Button btScan;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_sticker_income)
    Button tvStickerIncome;
    @BindView(R.id.bt_income_record)
    Button btIncomeRecord;
    @BindView(R.id.bt_notification)
    Button btNotification;
    @BindView(R.id.tv_more)
    AppCompatButton tvMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        init();
    }

    private void init() {

    }

    int lastClicked = -1;

    @OnClick({R.id.tv_sticker_income, R.id.bt_income_record, R.id.bt_notification,
            R.id.tv_shop_name, R.id.tv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sticker_income:
//                setStyle(view);
                break;
            case R.id.bt_income_record:
//                setStyle(view);
                break;
            case R.id.bt_notification:
                break;
            case R.id.tv_shop_name:
                startActivity(new Intent(this, ShopActivity.class));
                break;
            case R.id.tv_more:
                startActivity(new Intent(this, MyShopActivity.class));
                break;
        }
    }

    private void setStyle(View view) {
        if (lastClicked == -1) {
            Drawable originalDrawable = ContextCompat.getDrawable(this, R.drawable.circle_style);
            Drawable tintDrawable = DrawableCompat.wrap(originalDrawable).mutate();
            DrawableCompat.setTint(tintDrawable, Color.parseColor("#E9E9E9"));
            view.setBackground(tintDrawable);

            lastClicked = view.getId();
        } else if (lastClicked != view.getId()) {
            Drawable originalDrawable = ContextCompat.getDrawable(this, R.drawable.circle_style);
            Drawable tintDrawable = DrawableCompat.wrap(originalDrawable).mutate();
            DrawableCompat.setTint(tintDrawable, Color.parseColor("#E9E9E9"));
            view.setBackground(tintDrawable);

            originalDrawable = ContextCompat.getDrawable(this, R.drawable.circle_style);
            tintDrawable = DrawableCompat.wrap(originalDrawable).mutate();
            DrawableCompat.setTint(tintDrawable, Color.parseColor("#FDB700"));
            findViewById(lastClicked).setBackground(tintDrawable);
            lastClicked = view.getId();
        }
    }

    @OnClick(R.id.bt_scan)
    public void onViewClicked() {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            Toast.makeText(MainActivity.this, "相机不支持", Toast.LENGTH_SHORT).show();
        }
    }

    private int REQUEST_CODE = 1001;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** 处理二维码扫描结果 **/
        if (requestCode == REQUEST_CODE) {
            // 处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
