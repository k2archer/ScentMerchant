package com.kwei.scentmerchant.model.bean;

public class LoginBody {
    String mobile;
    String verifyCode;

    public LoginBody(String mobile, String verifyCode) {
        this.mobile = mobile;
        this.verifyCode =verifyCode;
    }
}
