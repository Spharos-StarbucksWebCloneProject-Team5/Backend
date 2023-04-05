package com.teamSiHyun.Starbucks.enums;

public enum PageNum {
    PAGE_SIZE(8);
    int value;
    private PageNum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
