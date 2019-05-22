package com.kwei.scentmerchant;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    ShopBean shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        if (shop == null) {
            tvActiveDesk.setEnabled(false);
            tvStart.setEnabled(false);
        }
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
            startActivityForResult(intent, SCAN_REQUEST_CODE);
        } else {
            Toast.makeText(this, "相机不支持", Toast.LENGTH_SHORT).show();
        }
    }

    static public final int ADD_SHOP_REQUEST_CODE = 1001;
    static public final int SCAN_REQUEST_CODE = 1002;
    public final String RESULT_CODE = "add_merchant";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_SHOP_REQUEST_CODE && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                tvActiveDesk.setEnabled(true);
            }
        }

        /** 处理二维码扫描结果 **/
        if (requestCode == SCAN_REQUEST_CODE && null != data) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    shop = new ShopBean();
                    shop.name = "巴雨山(新奥工美店)";
                    shop.address = "北京市东城区东华门街道正义路12号中华人民共和国";
                    shop.foodType = "粤菜";
                    shop.advancedMessage = "未填写";
                    tvStart.setEnabled(true);
                    // 显示广告贴纸信息
                    startActivity(new Intent(this, StickerDetailActivity.class));
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
