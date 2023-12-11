package edu.upc.dsa.CRUD.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ObjectHelper {

    // Get the name of all fields of an object/entity
    public static String[] getFields(Object object){
        Class theClass = object.getClass();
        Field[] fields = theClass.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for(int i = 0; i < fields.length; i++){
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    // Set the value of an object's property using reflection
    public static void setter(Object object, String property, Object value) {
        List<Method> methods = new ArrayList<>(Arrays.asList(object.getClass().getMethods()));
        try {
            Method m = methods.stream().filter((Method method) -> method.getName().contains("set" + getMethodName(property))).findFirst().get();
            m.invoke(object, value);
        }catch (NoSuchElementException | IllegalAccessException | InvocationTargetException e){
            //logger.warn("No setter found for: " + property + " in " + object.getClass().getName());
        }

        /*
        Method m = Arrays.stream(object.getClass().getMethods()).filter(x -> x.getName().matches("(?i).*set" + property + ".*")).findFirst().orElse(null);
        assert m != null;
        m.invoke(object, value);
         */
    }

    // Get the value of an object's property using reflection
    public static Object getter(Object object, String property) {
        List<Method> methods = new ArrayList<>(Arrays.asList(object.getClass().getMethods()));
        try {
            Method m = methods.stream().filter((Method method) -> method.getName().contains("get" + getMethodName(property))).findFirst().get();
            return m.invoke(object);
        }catch (NoSuchElementException | IllegalAccessException | InvocationTargetException e){
            //logger.warn("No getter found for: " + property + " in " + object.getClass().getName());
        }
        return null;

        /*
        Method m = Arrays.stream(object.getClass().getMethods()).filter(x -> x.getName().matches("(?i).*get" + property + ".*")).findFirst().orElse(null);
        assert m != null;
        return m.invoke(object, (Object[]) null);
         */
    }

    public static String getMethodName(String property) {
        return property.substring(0,1).toUpperCase()+property.substring(1);
    }

    // Get the name of the id attribute of an object/entity
    public static String getIdAttributeName(Class theClass){
        String[] fields = getFields(theClass);
        for (String field : fields) {
            if (field.matches("(?i).*id.*")) {
                return field;
            }
        }
        return null;
        /*
        Fields field=Arrays.stream(theClass.getDeclaredFields()).filter(x -> x.getName().matches("(?i).*id.*")).findFirst().orElse(null);
        assert field != null;
        return field.getName();
         */
    }

    // Get the name of an attribute of a class
    public static String getAttributeName(Class theClass, String attribute){
        String[] fields = getFields(theClass);
        for (String field : fields) {
            if (field.matches("(?i).*" + attribute + ".*")) {
                return field;
            }
        }
        return null;
        /*
        Fields field=Arrays.stream(theClass.getDeclaredFields()).filter(x -> x.getName().matches("(?i).*" + attribute + ".*")).findFirst().orElse(null);
        assert field != null;
        return field.getName();
         */
    }

    // Create a list of objects from a ResultSet
    public static List<Object> createObjects(ResultSet resultSet, Class theClass) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException {
        List<Object> objects = new ArrayList<>();
        String[] fields = getFields(theClass.newInstance());

        while(resultSet.next()){
            Object object = theClass.newInstance();
            for(String field : getFields(object)){
                setter(object, field, resultSet.getObject(field));
            }
            objects.add(object);
        }
        return objects;
    }


    // Check if two objects are equal based on their fields
    public static boolean equals(Object object1, Object object2){
        String[] fields1 = getFields(object1);
        String[] fields2 = getFields(object2);
        if(fields1.length != fields2.length){
            return false;
        }
        for(int i = 0; i < fields1.length; i++){
            if(!getter(object1, fields1[i]).equals(getter(object2, fields2[i]))){
                return false;
            }
        }
        return true;
    }
}
