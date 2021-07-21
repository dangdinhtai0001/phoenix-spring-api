package com.phoenix.api.core.model;

public enum SearchOperation {

    BETWEEN("BETWEEN", "BETWEEN"),
    EQUAL("=", "EQ"),
    GREATER_THAN_OR_EQUAL(">=", "GE"),
    GREATER_THAN(">", "GT"),
    IN("IN", "IN"),
    LESS_THAN_OR_EQUAL("<=", "LE"),
    LIKE("LIKE", "LIKE"),
    LESS_THAN("<", "LT"),
    NOT_EQUAL("!=", "NE"),
    NOT_IN("NOT_IN", "NOT_IN"),
    NOT_LIKE("NOT_LIKE", "NOT_LIKE");

    private final String sign;
    private final String value;

    private SearchOperation(String sign, String value) {
        this.sign = sign;
        this.value = value;
    }

    public String getSign() {
        return sign;
    }

    public String getValue() {
        return value;
    }
}

