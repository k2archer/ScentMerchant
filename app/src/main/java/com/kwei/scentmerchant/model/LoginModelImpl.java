package com.kwei.scentmerchant.model;

import com.kwei.scentmerchant.bean.BaseMessage;
import com.kwei.scentmerchant.contract.LoginContract;
import com.kwei.scentmerchant.utils.ToolUtils;

public class LoginModelImpl implements LoginContract.LoginModel {
    @Override
    public boolean getVerifyCode(String mobile, LoginContract.LoginPresenter presenter) {

        if (!ToolUtils.isMobileNumber(mobile)) {
            presenter.onFail("请输入正确手机号");
            return false;
        }

        BaseMessage requestResult = requestSendVerifyCode();
        if (requestResult != null) {
            presenter.onFail(requestResult.message);
            return false;
        } else {
            presenter.onFail("验证短信已发送");
        }

        return true;
    }

    private BaseMessage requestSendVerifyCode() {
        // todo 发送申请验证码请求到远端服务器
        return null;
    }

    @Override
    public void login(String mobile, String verifyCode, LoginContract.LoginPresenter presenter) {

        if (!ToolUtils.isMobileNumber(mobile)) {
            presenter.onFail("请输入正确手机号");
            return;
        }
        if (!verifyVerifyCode(verifyCode)) {
            presenter.onFail("请输入正确验证码");
            return;
        }

        BaseMessage requestResult = requestLogin(mobile, verifyCode);
        if (requestResult != null) {
            presenter.onFail(requestResult.message);
        } else {
            presenter.onSuccess();
        }
    }

    private BaseMessage requestLogin(String mobile, String verifyCode) {
        // todo 发送登录请求到远端服务器
        return null;
    }

    private boolean verifyVerifyCode(String code) {
        // todo 仅验证验证码有效性
        return code.length() == 6;
    }
}
