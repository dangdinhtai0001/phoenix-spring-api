/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.time.imp;

import com.phoenix.time.TimeProvider;

import java.time.Instant;

/**
 * A time implementation that uses the system clock and sleep call
 */
public class SystemTimeProvider implements TimeProvider {
    @Override
    public long milliseconds() {
        return System.currentTimeMillis();
    }

    @Override
    public long getTime() {
        return Instant.now().getEpochSecond();
    }

    @Override
    public long nanoseconds() {
        return System.nanoTime();
    }

    @Override
    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // no stress
        }
    }
}
