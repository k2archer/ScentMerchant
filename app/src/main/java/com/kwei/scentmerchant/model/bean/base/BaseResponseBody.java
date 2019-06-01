package com.kwei.scentmerchant.model.bean.base;

public class BaseResponseBody {
    public String token;
    public int code;
    public String message;

    public static int SUCCESS_CODE = 100200;
    public static int FAIL_CODE = 100300;
    public static int PARAMETER_ERROR_CODE = 100400;
}
