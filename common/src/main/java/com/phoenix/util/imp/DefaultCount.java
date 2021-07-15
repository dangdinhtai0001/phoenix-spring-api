/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.util.imp;

import com.phoenix.util.Count;

import java.io.Serializable;

/**
 * A mutable value of type {@code int}, for multisets to use in tracking counts of values.
 */
public class DefaultCount implements Serializable, Count {
    private int value;

    public DefaultCount(int value) {
        this.value = value;
    }

    @Override
    public void add(int delta) {
        value += delta;
    }

    @Override
    public int addAndGet(int delta) {
        return value += delta;
    }

    @Override
    public void set(int newValue) {
        value = newValue;
    }

    @Override
    public int getAndSet(int newValue) {
        int result = value;
        value = newValue;
        return result;
    }

    @Override
    public int get() {
        return value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DefaultCount && ((DefaultCount) obj).value == value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
