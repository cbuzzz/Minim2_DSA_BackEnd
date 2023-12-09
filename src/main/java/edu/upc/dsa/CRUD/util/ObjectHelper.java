package edu.upc.dsa.CRUD.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ObjectHelper {
    public static String[] getFields(Object entity){
        Class theClass = entity.getClass();

        Method[] methods = theClass.getDeclaredMethods();

        Field[] fields = theClass.getDeclaredFields();

        String [] sFields = new String[fields.length];
        int i = 0;
        for (Field f: fields) {
            sFields[i++] = f.getName();
        }
        return sFields;
    }

    public static void setter(Object object, String property, Object value) {
        List<Method> methods = new ArrayList<>(Arrays.asList(object.getClass().getMethods()));
        try {
            Method m = methods.stream().filter((Method method) -> method.getName().contains("set" + getMethodName(property))).findFirst().get();
            m.invoke(object,  value);
        }catch (NoSuchElementException | IllegalAccessException | InvocationTargetException e){
            //logger.warn("No setter found for: " + property + " in " + object.getClass().getName());
        }
    }

    public static Object getter(Object object, String property) {
        // Method // invoke
        String propToUppercase = property.substring(0, 1).toUpperCase() + property.substring(1);
        String getterName = "get" + propToUppercase;
        try {
            Method m = object.getClass().getDeclaredMethod(getterName);
            Object o = m.invoke(object);
            return o;

        }catch (NoSuchMethodException e){
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getMethodName(String property) {
        return property.substring(0,1).toUpperCase()+property.substring(1);
    }
}
