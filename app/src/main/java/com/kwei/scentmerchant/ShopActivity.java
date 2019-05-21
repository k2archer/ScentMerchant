package com.kwei.scentmerchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class ShopActivity extends AppCompatActivity {

    @BindView(R.id.bt_submit)
    Button btSubmit;

    ShopBean shop;
    @BindView(R.id.ed_shop_name)
    EditText edShopName;
    @BindView(R.id.ed_shop_address)
    EditText edShopAddress;
    @BindView(R.id.sp_shop_food_type)
    AppCompatSpinner edShopFoodType;
    @BindView(R.id.ed_shop_table_amount)
    EditText edShopTableAmount;
    @BindView(R.id.ed_average_consumption)
    EditText edShopAverageConsumption;
    @BindView(R.id.ed_shop_advanced_message)
    EditText edShopAdvancedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);

        shop = new ShopBean();
    }

    @OnItemSelected(R.id.sp_shop_food_type)
    public void onFoodTypeSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] languages = getResources().getStringArray(R.array.food_types);
        Toast.makeText(ShopActivity.this,
                "你点击的是:" + languages[position], Toast.LENGTH_SHORT).show();
        if (shop != null) {
            shop.foodType = languages[position];
        }
    }

    @OnClick(R.id.bt_submit)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                if (!checkShopInfo()) {
                    break;
                }
                getShopInfo();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                setResult(StartActivity.ADD_SHOP_REQUEST_CODE, intent);
                finish();
                break;
        }
    }

    private boolean checkShopInfo() {
        if (edShopName.getText().length() == 0) {
            Toast.makeText(this, "店铺名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (edShopAddress.getText().length() == 0) {
            Toast.makeText(this, "地址不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if  (edShopTableAmount.getText().length() == 0) {
            Toast.makeText(this, "桌位数不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if  (edShopAverageConsumption.getText().length() == 0) {
            Toast.makeText(this, "人均消费不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getShopInfo() {
        // todo ... 检查必填店铺信息
        shop.name = edShopName.getText().toString();
        shop.address = edShopAddress.getText().toString();
        shop.advancedMessage = edShopAdvancedMessage.getText().toString();
        shop.averageConsumption = Integer.valueOf(edShopAverageConsumption.getText().toString());
        shop.tableAmount = Integer.valueOf(edShopTableAmount.getText().toString());
        shop.phones = getPhones();
    }

    private String[] getPhones() {
        /// todo ... 读取 ImageView 照片文件名，添加进去
        return new String[0];
    }

}
