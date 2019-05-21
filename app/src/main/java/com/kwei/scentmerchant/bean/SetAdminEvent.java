package com.kwei.scentmerchant.bean;

public class SetAdminEvent {
    private String name;

    public SetAdminEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
