/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 12:40 PM
 */

package com.phoenix.api.repositories.base;

import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.structure.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class TestAbstractRepository {
    @Autowired
    private ExceptionRepo exceptionRepo;

    @Test
    public void test() throws Exception {
        System.out.println(exceptionRepo.findById(1L));
    }

    @Test
    public void testNativeQuery() throws Exception {
        List<Object[]> result = exceptionRepo.executeNativeQuery("select * from fw_exception");
        print(result);

        Object[] record = result.get(0);
    }

    @Test
    public void testParseResult() throws NoSuchFieldException, IllegalAccessException {
        List<Object[]> result = exceptionRepo.executeNativeQuery("select * from fw_exception");
        print(result);

        Object[] record = result.get(0);

        ExceptionEntity exceptionEntity = new ExceptionEntity();

        List<Pair<String, Class>> params = new LinkedList<>();
        params.add(new Pair<>("code", String.class));
        params.add(new Pair<>("resource", String.class));
        params.add(new Pair<>("message", String.class));
        params.add(new Pair<>("httpCode", int.class));
        params.add(new Pair<>("id", long.class));

        exceptionRepo.parseResult(record, params, exceptionEntity);

        System.out.println(exceptionEntity);
    }


    private void print(List<Object[]> list) {
        for (Object[] record : list) {
            System.out.println(Arrays.asList(record));
        }
    }
}
