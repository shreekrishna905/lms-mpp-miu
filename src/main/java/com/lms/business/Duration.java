package com.lms.business;

import java.io.Serializable;

public enum Duration implements Serializable {
    TWENTY_ONE("21"), SEVEN("7");
    private String value;

    Duration(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }
}
