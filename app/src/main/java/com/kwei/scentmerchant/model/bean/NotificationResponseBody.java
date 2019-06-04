package com.kwei.scentmerchant.model.bean;

import com.kwei.scentmerchant.bean.NotificationItem;
import com.kwei.scentmerchant.model.bean.base.BaseResponseBody;

import java.util.List;

public class NotificationResponseBody extends BaseResponseBody {
    public List<NotificationItem> data;
}
