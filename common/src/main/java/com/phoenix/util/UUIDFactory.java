/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.util;

import java.util.UUID;

/**
 * Common interface to sources of various versions of UUIDs.
 */
public interface UUIDFactory {
    /**
     * Generates a new version 4 UUID.
     *
     * @return the newly generated UUID
     */
    UUID generateRandomUuid();
}
