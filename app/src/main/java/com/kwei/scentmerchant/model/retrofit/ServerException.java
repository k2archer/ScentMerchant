package com.kwei.scentmerchant.model.retrofit;


public class ServerException extends Exception {

    private int code;
    private String message = "";

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }
}
