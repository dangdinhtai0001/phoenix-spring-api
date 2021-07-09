/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 9:14 PM
 */

package com.phoenix.api.base.repositories;

/**
 * Định nghĩa các toán tử tìm kiếm trong cơ sở dũ liệu
 */
public enum SearchOperation {
    EQUALITY, GREATER_THAN, LESS_THAN, IN, NOT_EQUAL, GREATER_THAN_OR_EQUAL, LESS_THAN_OR_EQUAL, NOT_IN;
}
