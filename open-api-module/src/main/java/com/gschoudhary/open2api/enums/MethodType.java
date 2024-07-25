package com.gschoudhary.open2api.enums;

public enum MethodType {
    POST("POST"), GET("GET"), UPDATE("UPDATE");

    private String value;

    MethodType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
