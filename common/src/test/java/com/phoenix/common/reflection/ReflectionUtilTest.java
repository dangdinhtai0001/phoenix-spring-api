/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 4:26 PM
 */

package com.phoenix.common.reflection;

import com.phoenix.common.structure.Pair;
import com.phoenix.common.util.ReflectionUtil;
import com.phoenix.common.util.imp.DefaultCount;
import junit.framework.TestCase;

import java.lang.reflect.Field;

public class ReflectionUtilTest extends TestCase {
    public void testGetField() {
        System.out.println(ReflectionUtil.getDeclaredFields(DefaultCount.class)[0]);
    }

    public void testGetAllField() {
        System.out.println(ReflectionUtil.getAllFields(DefaultCount.class).get(0));
    }

    public void testSetField() throws NoSuchFieldException {
        DefaultCount defaultCount = new DefaultCount(0);

        Class aClass = defaultCount.getClass();
        Field field = aClass.getDeclaredField("value");
        field.setAccessible(true);

        System.out.println(field.getName());
        System.out.println(field.getType());

        String value = "1";
        Class aClass1 = int.class;
        Pair<String, Class> pair = new Pair(value, aClass1);

        System.out.println(aClass1.cast(value));
    }

}