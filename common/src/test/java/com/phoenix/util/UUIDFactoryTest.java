package com.phoenix.util;

import com.phoenix.util.imp.ConcurrentUUIDFactory;
import org.junit.Test;

import java.util.UUID;

public class UUIDFactoryTest {

    @Test
    public void testConcurrentUUIDFactory(){
        UUIDFactory uuidFactory = new ConcurrentUUIDFactory();
        UUID uuid = uuidFactory.generateRandomUuid();

        System.out.println(uuid.toString());
    }
}
