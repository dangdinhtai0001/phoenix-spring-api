/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 12:40 PM
 */

package com.phoenix.api.repositories.base;

import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.repositories.common.ExceptionRepositoryImp;
import com.phoenix.reflection.ReflectionUtil;
import com.phoenix.structure.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class TestAbstractRepository {
    @Autowired
    private ExceptionRepositoryImp exceptionRepo;

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
    public void testParseSingleResult() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        List<Object[]> result = exceptionRepo.executeNativeQuery("select code_,resource_, message_, http_code, id  from fw_exception");
        print(result);

        Object[] record = result.get(0);

        List<Pair<String, Class>> params = new LinkedList<>();
        params.add(new Pair<>("code", String.class));
        params.add(new Pair<>("resource", String.class));
        params.add(new Pair<>("message", String.class));
        params.add(new Pair<>("httpCode", int.class));
        params.add(new Pair<>("id", long.class));

        System.out.println(exceptionRepo.parseResult(record, params, ExceptionEntity.class));
    }

    @Test
    public void testParseResult() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        List<Object[]> result = exceptionRepo.executeNativeQuery("select code_,resource_, message_, http_code, id  from fw_exception");
        print(result);

        List<Pair<String, Class>> params = new LinkedList<>();
        params.add(new Pair<>("code", String.class));
        params.add(new Pair<>("resource", String.class));
        params.add(new Pair<>("message", String.class));
        params.add(new Pair<>("httpCode", int.class));
        params.add(new Pair<>("id", long.class));

        System.out.println(exceptionRepo.parseResult(result, params, ExceptionEntity.class));
    }

    @Test
    public void testUpdateNativeQuery() {
        int row = exceptionRepo.updateNativeQuery("update fw_exception set message_ = ? where code_ = ?;",
                "Wrong user credentials", "AUTH_001");

        System.out.println(row);
    }

    @Test
    public void testFindAll() {
        List<ExceptionEntity> list = (List<ExceptionEntity>) exceptionRepo.findAll();

        System.out.println(list);
    }

    @Test
    public void testConvertMapToObject() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        Map<String, String> map = new LinkedHashMap<>();

        map.put("code", "Auth_002");
        map.put("resource", "resource");
        map.put("message", "message");
        map.put("httpCode", "400");
        map.put("id", "2");

        ExceptionEntity exceptionEntity = (ExceptionEntity) ReflectionUtil.convertMapToObject(map, ExceptionEntity.class);

        System.out.println(exceptionEntity);

    }



    private void print(List<Object[]> list) {
        for (Object[] record : list) {
            System.out.println(Arrays.asList(record));
        }
    }
}
