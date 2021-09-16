/*
 * @Author: Đặng Đình Tài
 * @Created date: 7/15/21, 10:27 AM
 */

package com.phoenix.common.time.imp;

import com.phoenix.common.time.TimeProvider;

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
