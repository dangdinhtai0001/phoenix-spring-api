/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 4:24 PM
 */

package com.phoenix.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ReflectionUtil {
    public static Field[] getDeclaredFields(Class aClass) {
        return aClass.getDeclaredFields();
    }

    public static List<Field> getAllFields(Class aClass) {
        if (aClass == null) {
            return new LinkedList<>();
        }

        Field[] fields = aClass.getDeclaredFields();

        LinkedList list = new LinkedList(Arrays.asList(fields));

        list.addAll(getAllFields(aClass.getSuperclass()));

        return list;
    }

    public static Field findFieldByName(String name, Class aClass) {
        List<Field> list = ReflectionUtil.getAllFields(aClass);

        Optional<Field> optional = list.stream()
                .filter(field -> name.equals(field.getName()))
                .findFirst();

        return optional.orElse(null);
    }

    public static void setField(String fieldName, Class classOfField, String value, Object obj) throws NoSuchFieldException, IllegalAccessException {
        Class objClass = obj.getClass();
        Field field;

        try {
            field = objClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = findFieldByName(fieldName, objClass);
        }

        if (field == null) {
            throw new NoSuchFieldException();
        }

        field.setAccessible(true);

        if (int.class.equals(classOfField)) {
            field.set(obj, Integer.parseInt(value));
        } else if (long.class.equals(classOfField)) {
            field.set(obj, Long.parseLong(value));
        } else {
            field.set(obj, classOfField.cast(value));
        }

    }
}
