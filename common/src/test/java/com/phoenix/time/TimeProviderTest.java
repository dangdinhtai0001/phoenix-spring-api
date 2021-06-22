package com.phoenix.time;

import com.phoenix.time.imp.SystemTimeProvider;
import org.junit.Test;

public class TimeProviderTest {
    @Test
    public void testSystemTime() {
        TimeProvider timeProvider = new SystemTimeProvider();

        System.out.println(timeProvider.milliseconds());
        timeProvider.sleep(1000);
        System.out.println(timeProvider.nanoseconds());
    }
}
