/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.util;

import com.phoenix.common.util.imp.ConcurrentUUIDFactory;
import org.junit.Test;

import java.util.UUID;

public class UUIDFactoryTest {

    @Test
    public void testConcurrentUUIDFactory() {
        UUIDFactory uuidFactory = new ConcurrentUUIDFactory();
        UUID uuid = uuidFactory.generateRandomUuid();

        System.out.println(uuid.toString());
    }
}
