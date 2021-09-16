package com.phoenix.core.model.query;

public enum JoinType {
    DEFAULT(false, false),
    INNER_JOIN(true, false),
    JOIN(true, false),
    LEFT_JOIN(false, true),
    RIGHT_JOIN(false, true),
    FULL_JOIN(false, true);

    private final boolean inner;
    private final boolean outer;

    private JoinType(boolean inner, boolean outer) {
        this.inner = inner;
        this.outer = outer;
    }

    public boolean isInner() {
        return this.inner;
    }

    public boolean isOuter() {
        return this.outer;
    }
}
