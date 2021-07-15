/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/27/21, 1:59 PM
 */

package com.phoenix.api.common;

import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.reflection.ReflectionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class TestCommon {
    @Test
    public void testGetAllFieldViaReflection(){
        List<Field> list = new LinkedList<>();
        list = ReflectionUtil.getAllFields(ExceptionEntity.class);

        for (Field field : list) {
            System.out.println(field);
        }
    }
}
