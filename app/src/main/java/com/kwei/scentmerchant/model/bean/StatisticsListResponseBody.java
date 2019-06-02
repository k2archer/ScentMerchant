package com.kwei.scentmerchant.model.bean;

import com.kwei.scentmerchant.bean.StatisticsItem;
import com.kwei.scentmerchant.model.bean.base.BaseResponseBody;

import java.util.List;

public class StatisticsListResponseBody extends BaseResponseBody {
    public List<StatisticsItem> data;
}
