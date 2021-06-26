/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 12:40 PM
 */

package com.phoenix.api.repositories.base;

import com.phoenix.api.entities.common.ExceptionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestAbstractRepository {
    @Autowired
    private  ExceptionRepo exceptionRepo;
    @Test
    public void test() throws Exception {
        System.out.println(exceptionRepo.findById(1L));
    }

    @Test
    public void testNativeQuery() throws Exception {

        System.out.println(exceptionRepo.executeNativeQuery("select * from fw_exception"));
    }
}
