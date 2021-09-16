package com.phoenix.common.structure;

import com.google.common.collect.ImmutableList;
import com.phoenix.common.exceptions.SupportException;

public interface Tuple {
    <T> T get(int index) throws SupportException;

    <T> T get(String expr) throws SupportException;

    <T> T get(int index, Class<T> type);

    <T> T get(String expr, Class<T> type);

    int size();

    Object[] toArray();

    boolean equals(Object var1);

    int hashCode();

    ImmutableList<String> getExpressions();

    String[] getArrayExpressions();
}
