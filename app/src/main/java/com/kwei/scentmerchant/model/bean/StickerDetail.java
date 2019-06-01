package com.kwei.scentmerchant.model.bean;

import java.util.Date;

public class StickerDetail {
    public String id;      // ID
    public int amount;     // 桌贴数量
    public String content; // 桌贴内容
    public Date date;      // 激活日期
    public int cycle;      // 投放周期
    public String model;   // 收益模式
    public String settlement;  // 收益结算
    public String scanMode;    // 扫码方式
    public String[] pictures;  // 环境照
    public String poster;      // 海报 URL
}
