package com.phoenix.business.drools;

import java.util.LinkedList;
import java.util.List;

public class FilterConditional {
    private boolean isPermitted;
    private List<String> conditional;

    public FilterConditional() {
        conditional = new LinkedList<>();
    }

    public FilterConditional(boolean isPermitted, List<String> value) {
        this.isPermitted = isPermitted;
        this.conditional = value;
    }

    public void addConditional(String value) {
        conditional.add(value);
    }

    public boolean isPermitted() {
        return isPermitted;
    }

    public void setPermitted(boolean permitted) {
        isPermitted = permitted;
    }

    public List<String> getConditional() {
        return conditional;
    }

    public void setConditional(List<String> conditional) {
        this.conditional = conditional;
    }

    @Override
    public String toString() {
        return "FilterConditional{" +
                "isPermitted=" + isPermitted +
                ", conditional=" + conditional +
                '}';
    }
}
