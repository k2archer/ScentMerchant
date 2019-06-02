package com.kwei.scentmerchant.model.bean;

import com.kwei.scentmerchant.bean.SettlementItem;
import com.kwei.scentmerchant.model.bean.base.BaseResponseBody;

import java.util.List;

public class SettlementListBody extends BaseResponseBody {
    public List<SettlementItem> data;
}
