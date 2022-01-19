package com.lms.business;

public enum Duration {
    TWENTY_ONE("21"), SEVEN("7");
    private String value;

    Duration(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
