package com.phoenix.business.drools;

public class FilterConditional {
    private boolean isPermitted;
    private String value;

    public FilterConditional() {
    }

    public FilterConditional(boolean isPermitted, String value) {
        this.isPermitted = isPermitted;
        this.value = value;
    }

    public boolean isPermitted() {
        return isPermitted;
    }

    public void setPermitted(boolean permitted) {
        isPermitted = permitted;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FilterConditional{" +
                "isPermitted=" + isPermitted +
                ", value='" + value + '\'' +
                '}';
    }
}
