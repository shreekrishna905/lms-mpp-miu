package com.lms.business;

import java.io.Serializable;
import java.util.stream.Stream;

public enum Duration implements Serializable {
    TWENTY_ONE("21"), SEVEN("7");
    private String value;

    Duration(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Duration withDays(String days) {
        return Stream.of(Duration.values())
                .filter(f -> f.getValue().equals(days))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }
}
