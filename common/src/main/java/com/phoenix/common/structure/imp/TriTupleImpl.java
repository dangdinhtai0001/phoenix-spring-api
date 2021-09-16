package com.phoenix.common.structure.imp;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;

public class TriTupleImpl extends AbstractTuple {

    protected ImmutableList<Class> types;

    public TriTupleImpl(String[] expression, Class[] types, Object... args) {
        this.args = args;
        this.expression = ImmutableList.copyOf(expression);
        this.types = ImmutableList.copyOf(types);
        this.bindings = this.createBindings(Arrays.asList(expression));
    }

    @Override
    public <T> T get(int index) {
        Class<T> type = this.types.get(index);

        return get(index, type);
    }

    @Override
    public <T> T get(String expr) {
        Integer idx = this.bindings.get(expr);
        if (idx != null) {
            Class<T> type = this.types.get(idx);
            return type.cast(this.args[idx]);
        }

        return null;
    }
}
