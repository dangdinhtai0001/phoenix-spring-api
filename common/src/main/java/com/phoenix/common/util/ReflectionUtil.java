/*
 * @Author: Đặng Đình Tài
 * @Created date: 7/15/21, 10:12 AM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 4:24 PM
 */

package com.phoenix.common.util;

import com.phoenix.common.structure.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class gồm các hàm static hỗ trợ reflection
 */
public class ReflectionUtil {

    /**
     * @param aClass Class cần tạo instance mới. Lưu ý, class này cần pahri có hàm dựng không đối số
     * @return Instance của class
     * @throws NoSuchMethodException     Nếu không tồn tại hàm dựng không đối số
     * @throws InvocationTargetException khi khởi tạo instance mới
     * @throws InstantiationException    khi khởi tạo instance mới
     * @throws IllegalAccessException    khi khởi tạo instance mới
     */
    public static Object createInstance(Class aClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = aClass.getConstructor();
        return constructor.newInstance();
    }

    /**
     * @param aClass Class cần xử lí
     * @return List tên các declared method của class
     */
    public static List getAllDeclaredMethodsMethodNames(Class aClass) {
        return Arrays.stream(aClass.getDeclaredMethods()).map(Method::getName).collect(Collectors.toList());
    }

    /**
     * @param aClass Class cần xử lí
     * @return List các declared method của class
     */
    public static List getAllDeclaredMethodsMethods(Class aClass) {
        return Arrays.asList(aClass.getDeclaredMethods());
    }

    /**
     * @param aClass Class cần xử lí
     * @return List tên các method của class
     */
    public static List getAllMethodNames(Class aClass) {
        return Arrays.stream(aClass.getMethods()).map(Method::getName).collect(Collectors.toList());
    }

    /**
     * @param aClass Class cần xử lí
     * @return List tên các method của class
     */
    public static List getAllMethods(Class aClass) {
        return Arrays.asList(aClass.getMethods());
    }

    /**
     * @param aClass Class cần xử lí
     * @return Mảng chứa tất cả các field của class (Không bao gồm các field của supper class)
     */
    public static Field[] getDeclaredFields(Class aClass) {
        return aClass.getDeclaredFields();
    }

    /**
     * @param aClass : Class
     * @return List chứa tất cả các field của class (Bao gồm các field của supper class), dùng đệ quy
     */
    public static List<Field> getAllFields(Class aClass) {
        if (aClass == null) {
            return new LinkedList<>();
        }

        Field[] fields = aClass.getDeclaredFields();

        LinkedList<Field> list = new LinkedList<>(Arrays.asList(fields));

        list.addAll(getAllFields(aClass.getSuperclass()));

        return list;
    }


    public static Class getTypeOfFieldByName(Class aClass, String name) throws NoSuchFieldException {
        List<Field> fields = getAllFields(aClass);

        Field field = fields.stream()
                .filter(f -> f.getName().equals(name))
                .findAny()
                .orElse(null);

        return field.getType();
    }

    /**
     * @param name   tên field cần tìm
     * @param aClass Class cần xử lí
     * @return Field có tên == name, nếu không tìm thấy trả về null
     */
    public static Field findFieldByName(String name, Class aClass) {
        List<Field> list = ReflectionUtil.getAllFields(aClass);

        Optional<Field> optional = list.stream()
                .filter(field -> name.equals(field.getName()))
                .findFirst();

        return optional.orElse(null);
    }


    /**
     * @param obj       Instance để set giá trị
     * @param fieldName Tên field cần set
     * @param value     Giá trị muốn set
     * @throws NoSuchFieldException   nếu không tìm thấy field
     * @throws IllegalAccessException Sai kiểu dữ liệu
     */
    public static void setField(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field;
        Class objClass;

        objClass = obj.getClass();
        try {
            field = objClass.getField(fieldName);
        } catch (NoSuchFieldException e) {
            field = findFieldByName(fieldName, objClass);
        }

        if (field == null) {
            throw new NoSuchFieldException(String.format("Can't find field: %s of class: ", fieldName, objClass.getSimpleName()));
        }

        field.setAccessible(true);

        field.set(obj, value);

    }

    /**
     * @param fieldName    Tên field cần set
     * @param classOfField Kiểu dữ liệu của field cần set
     * @param value        Giá trị muốn set (dạng String)
     * @param obj          Instance để set giá trị
     * @throws NoSuchFieldException nếu không tìm thấy field
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
            throw new NoSuchFieldException(String.format("Can't find field: %s of class: ", fieldName, objClass.getSimpleName()));
        }

        field.setAccessible(true);

        if (int.class.equals(classOfField) || Integer.class.equals(classOfField)) {
            field.set(obj, Integer.parseInt(value));
        } else if (long.class.equals(classOfField) || Long.class.equals(classOfField)) {
            field.set(obj, Long.parseLong(value));
        } else if (double.class.equals(classOfField) || Double.class.equals(classOfField)) {
            field.set(obj, Double.parseDouble(value));
        } else if (Date.class.equals(classOfField)) {
            field.set(obj, DateUtil.convertString2Date(value, DateUtil.DEFAULT_DATE_FORMAT));
        } else {
            field.set(obj, classOfField.cast(value));
        }

    }

    /**
     * @param field Đối tượng {@link Field} cần cập nhật giá trị
     * @param value giá trị cần set (dạng String)
     * @param obj   Instance cần cập nhật giá trị
     * @throws NoSuchFieldException   khi không tìm thấy Filed trong class
     * @throws IllegalAccessException Khi có lỗi parse String value sang giá trị phù hợp
     */
    public static void setField(Field field, String value, Object obj) throws NoSuchFieldException, IllegalAccessException {
        setField(field.getName(), field.getType(), value, obj);
    }

    /**
     * @param map    {@link Map} chứa thông tin của đối tượng cần convert với key => tên trường, value => giá trị của trường đó
     * @param aClass Class cần convert thành
     * @return Một instance của aCLass
     * @throws InstantiationException Khi aClass không có hàm dựng không đối số
     * @throws IllegalAccessException Khi có lỗi parse String value sang giá trị phù hợp
     * @throws NoSuchFieldException   khi không tìm thấy Filed trong class
     */
    public static Object convertMapToObject(Map<String, String> map, Class aClass) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Object target = aClass.newInstance();

        List<Field> fields = getAllFields(aClass);

        for (Field field : fields) {
            setField(field, map.get(field.getName()), target);
        }

        return target;

    }

    /**
     * @param source Instance của aClass cần convert thành map
     * @param aClass Class của đối tượng cần xử lí
     * @return Một map chứa thông tin của đối tượng cần convert với key => tên trường, value => giá trị của trường đó
     * @throws InstantiationException Khi aClass không có hàm dựng không đối số
     * @throws IllegalAccessException có lỗi lúc lấy giá trị
     */
    public static Map<String, String> convertObjectToMap(Object source, Class aClass) throws InstantiationException, IllegalAccessException {
        Map<String, String> map = new LinkedHashMap<>();

        List<Field> fields = getAllFields(aClass);

        Class<?> targetType;
        Object objectValue;
        for (Field field : fields) {
            field.setAccessible(true);
            targetType = field.getType();
            objectValue = targetType.newInstance();

            map.put(field.getName(), String.valueOf(field.get(objectValue)));
        }

        return map;
    }

    public static List<Pair<String, Class>> getFieldAsPairList(Class aClass, String... properties) {
        List<Field> allFields = getAllFields(aClass);
        List<Pair<String, Class>> result = new LinkedList<>();


        for (String property : properties) {
            for (Field field : allFields) {
                if (field.getName().equals(property)) {
                    result.add(new Pair<>(field.getName(), field.getType()));
                }
            }
        }
        return result;
    }

    public static Map getFieldAsMap(Class aClass, String... properties) {
        Map<String, Class> result = new LinkedHashMap();
        List<Field> allFields = getAllFields(aClass);


        for (String property : properties) {
            for (Field field : allFields) {
                if (field.getName().equals(property)) {
                    result.put(field.getName(), field.getType());
                }
            }
        }
        return result;
    }
}
