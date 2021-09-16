package com.phoenix.common.structure.imp;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.phoenix.common.structure.Tuple;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class AbstractTuple implements Tuple, Serializable {
    protected Object[] args;
    protected ImmutableList<String> expression;
    protected ImmutableMap<String, Integer> bindings;


    protected ImmutableMap<String, Integer> createBindings(List<String> expressions) {
        Map<String, Integer> map = Maps.newHashMap();

        for (int i = 0; i < expressions.size(); ++i) {
            String e = expressions.get(i);
            map.put(e, i);
        }

        return ImmutableMap.copyOf(map);
    }

    @Override
    public <T> T get(int index, Class<T> type) {
        if (index >= size()) {
            return null;
        }
        return type.cast(this.args[index]);
    }

    @Override
    public <T> T get(String expr, Class<T> type) {
        Integer idx = this.bindings.get(expr);

        if (idx != null) {
            return type.cast(this.args[idx]);
        }

        return null;
    }

    @Override
    public int size() {
        return this.args.length;
    }

    @Override
    public Object[] toArray() {
        return this.args;
    }

    @Override
    public ImmutableList<String> getExpressions() {
        return this.expression;
    }

    @Override
    public String[] getArrayExpressions() {
        return this.expression.toArray(new String[0]);
    }
}
