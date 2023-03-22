package com.example.Starbucks.enums;

public enum Redis {
    EXPIRE_LIMIT(20);
    int value;
    private Redis(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
