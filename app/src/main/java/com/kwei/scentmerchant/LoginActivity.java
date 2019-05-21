package com.kwei.scentmerchant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.ed_login_phone)
    EditText edLoginPhone;
    @BindView(R.id.ed_login_verification_code)
    EditText edLoginVerificationCode;
    @BindView(R.id.bt_get_verification_code)
    Button btGetVerificationCode;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_no_receive_message)
    TextView tvNoReceiveMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_no_receive_message)
    public void onClicked(View v) {

    }

    @OnClick(R.id.bt_login)
    public void login(View v) {
        String accountPhone = edLoginPhone.getText().toString();
        String VerificationCode = edLoginVerificationCode.getText().toString();
        if (accountPhone.equals("188") && VerificationCode.equals("188")) {
            startActivity(new Intent(this, StartActivity.class));
        } else {
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
        }
}

    TimerTask task;
    int countdown = 60;
    Timer timer;
    @OnClick(R.id.bt_get_verification_code)
    public void sendVerificationMessage() {
        // todo 验证手机号有效性
        String number = edLoginPhone.getText().toString();
        if (number.length() == 0 || number.length() != 13) {
            Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if (timer == null) timer = new Timer();
        if (task == null) {
            countdown = 60;
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
            timer.schedule(task,0, 1 * 1000);
            Toast.makeText(this, "验证短信已发送", Toast.LENGTH_SHORT).show();
        }
    }

}
