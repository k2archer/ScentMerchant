package com.kwei.scentmerchant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kwei.scentmerchant.contract.LoginContract;
import com.kwei.scentmerchant.presenter.LoginPresenterImpl;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    @BindView(R.id.ed_login_mobile)
    EditText edLoginMobile;
    @BindView(R.id.ed_login_verification_code)
    EditText edLoginVerificationCode;
    @BindView(R.id.bt_get_verification_code)
    Button btGetVerificationCode;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_no_receive_message)
    TextView tvNoReceiveMessage;

    LoginPresenterImpl loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
    }

    @OnClick(R.id.tv_no_receive_message)
    public void onClicked(android.view.View v) {

    }

    @OnClick(R.id.bt_login)
    public void login(android.view.View v) {
        loginPresenter.login();
    }

    TimerTask task;
    int countdown;
    Timer timer;

    @OnClick(R.id.bt_get_verification_code)
    public void onGetVerifyCodeClicked() {
        if (loginPresenter.getVerifyCode()) {
            if (timer == null) timer = new Timer();
            if (task == null) {
                countdown = 60; // 重新获取验证码间隔时间(秒)
                task = new TimerTask() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        if (countdown == 0) {
                            task.cancel();
                            task = null;
                            runOnUiThread(() -> btGetVerificationCode.setText("获取验证码"));
                        } else {
                            countdown--;
                            runOnUiThread(() -> btGetVerificationCode.setText("获取验证码(" + countdown + ")"));
                        }
                    }
                };
                timer.schedule(task, 0, 1000);
            }
        }


    }

    @Override
    public String getMobile() {
        return edLoginMobile.getText().toString();
    }

    @Override
    public String getVerifyCode() {
        return edLoginVerificationCode.getText().toString();
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("login_mobile", edLoginMobile.getText().toString());
        startActivity(intent);
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
    public void newMerchant() {
        Intent intent = new Intent(this, StartActivity.class);
        intent.putExtra("login_mobile", edLoginMobile.getText().toString());
        startActivity(intent);
        finish();
    }
}
