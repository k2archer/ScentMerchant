package com.kwei.scentmerchant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.bean.MerchantDetail;
import com.kwei.scentmerchant.contract.MerchantDetailContract;
import com.kwei.scentmerchant.presenter.MerchantDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterpriseAccountDetailActivity extends AppCompatActivity implements MerchantDetailContract.View {

    @BindView(R.id.sp_account_type)
    AppCompatSpinner spAccountType;
    @BindView(R.id.et_enterprise_account_name)
    EditText tvEnterpriseAccountName;
    @BindView(R.id.et_license_code)
    EditText tvLicenseCode;
    @BindView(R.id.sp_bank_name)
    AppCompatSpinner spBankName;
    @BindView(R.id.tv_bank_account)
    EditText tvBankAccount;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    private MerchantDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_account_detail);
        ButterKnife.bind(this);

        presenter = new MerchantDetailPresenter(this);

        presenter.getMerchantDetail();
    }

    @Override
    public void showMerchantDetail(MerchantDetail merchant) {
        if (merchant != null) {
            setSpinnerItemSelectedByValue(spAccountType, merchant.accountType);
            tvEnterpriseAccountName.setText(merchant.name);
            tvLicenseCode.setText(merchant.licenseCode);
            setSpinnerItemSelectedByValue(spBankName, merchant.bankName);
            tvBankAccount.setText(merchant.bankAccount);
            // todo merchant.permission
            // todo merchant.license

            // 设置不可编辑状态
            tvEnterpriseAccountName.setFocusable(false);
            tvEnterpriseAccountName.setFocusableInTouchMode(false);
            tvLicenseCode.setFocusable(false);
            tvLicenseCode.setFocusableInTouchMode(false);
            tvBankAccount.setFocusable(false);
            tvBankAccount.setFocusableInTouchMode(false);
        }
    }

    public static void setSpinnerItemSelectedByValue(AppCompatSpinner spinner,String value){
        SpinnerAdapter apsAdapter= spinner.getAdapter(); // 得到 SpinnerAdapter 对象
        int k= apsAdapter.getCount();
        for(int i=0;i<k;i++){
            if(value.equals(apsAdapter.getItem(i).toString())){
                spinner.setSelection(i,true); // 默认选中项
                break;
            }
        }
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
    public void onSubmitSucceed() {
        onBackPressed();
    }

    @OnClick(R.id.tv_submit)
    public void onSubmitClicked() {
        presenter.summitVerify();
    }
}
