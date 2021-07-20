/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.util;

/**
 * A mutable value of type {@code int}, for multisets to use in tracking counts of values.
 */
public interface Count {

    /**
     * @param delta :
     *
     * - tăng biến đếm lên delta đơn vị
     */
    void add(int delta);

    /**
     * @param delta : giá trị mới
     * @return : tra về giá trị sau khi đã tăng biến đếm lên delta đơn vị
     */
    int addAndGet(int delta);

    /**
     * @param newValue : set giá trị mới
     */
    void set(int newValue);

    /**
     * @param newValue : giá trị mới
     * @return: trả về giá trị trước khi tăng biến đếm lên dlta đơn vị
     */
    int getAndSet(int newValue);

    /**
     * @return : trả về giá trị hiện tại
     */
    int get();
}
