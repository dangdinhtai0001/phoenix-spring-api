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
    /**
     * @param aClass : Class
     * @return : Mảng chứa tất cả các field của class (Không bao gồm các field của supper class)
     */
    public static Field[] getDeclaredFields(Class aClass) {
        return aClass.getDeclaredFields();
    }

    /**
     * @param aClass : Class
     * @return : Mảng chứa tất cả các field của class (Bbao gồm các field của supper class), dùng đệ quy
     */
    public static List<Field> getAllFields(Class aClass) {
        if (aClass == null) {
            return new LinkedList<>();
        }

        Field[] fields = aClass.getDeclaredFields();

        LinkedList list = new LinkedList(Arrays.asList(fields));

        list.addAll(getAllFields(aClass.getSuperclass()));

        return list;
    }

    /**
     * @param name : tên field cần tìm
     * @param aClass : Class
     * @return : Field có tên == name, nếu không tìm thấy trả về null
     */
    public static Field findFieldByName(String name, Class aClass) {
        List<Field> list = ReflectionUtil.getAllFields(aClass);

        Optional<Field> optional = list.stream()
                .filter(field -> name.equals(field.getName()))
                .findFirst();

        return optional.orElse(null);
    }

    /**
     * @param fieldName : Tên field cần set
     * @param classOfField: Kiểu dữ liệu của field cần set
     * @param value: Giá trị muốn set (dạng String)
     * @param obj: Instance để set giá trị
     * @throws NoSuchFieldException: nếu không tìm thấy field
     * @throws IllegalAccessException
     */
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
