/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.time;

import com.phoenix.common.time.imp.SystemTimeProvider;
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
