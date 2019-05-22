package com.kwei.scentmerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.StartActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyShopActivity extends AppCompatActivity {

    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_shop_id)
    TextView tvShopId;
    @BindView(R.id.s_income_notify)
    Switch sIncomeNotify;
    @BindView(R.id.tv_alter_shop)
    TextView tvAlterShop;
    @BindView(R.id.tv_alter_administrator)
    TextView tvAlterAdministrator;
    @BindView(R.id.tv_question)
    TextView tvQuestion;
    @BindView(R.id.tv_customer_service)
    TextView tvCustomerService;
    @BindView(R.id.tv_change_shop)
    TextView tvChangeShop;
    @BindView(R.id.tv_exit)
    TextView tvExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_shop_name, R.id.tv_shop_id, R.id.s_income_notify,
            R.id.tv_alter_shop, R.id.tv_alter_administrator, R.id.tv_question,
            R.id.tv_customer_service, R.id.tv_change_shop, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shop_name:
                break;
            case R.id.s_income_notify:
                break;
            case R.id.tv_alter_shop:
                startActivity(new Intent(this, ShopActivity.class));
                break;
            case R.id.tv_alter_administrator:
                startActivity(new Intent(this, AdministratorActivity.class));
                break;
            case R.id.tv_question:
                break;
            case R.id.tv_customer_service:
                break;
            case R.id.tv_change_shop:
                startActivity(new Intent(this, StartActivity.class));
                break;
            case R.id.tv_exit:

                break;
        }
    }
}
