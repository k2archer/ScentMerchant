package com.kwei.scentmerchant.model.bean;

public class BaseMessage {
    public String isSucceed;
    public String message;

    public BaseMessage(boolean isSucceed, String message) {
        this.isSucceed = isSucceed ? "true" : "false";
        this.message = message;
    }

}