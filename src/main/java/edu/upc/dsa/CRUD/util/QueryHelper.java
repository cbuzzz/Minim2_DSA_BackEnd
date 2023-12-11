package edu.upc.dsa.CRUD.util;

import java.util.HashMap;
import java.util.Objects;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.InvocationTargetException;

public class QueryHelper {

    public QueryHelper() {
        //Constructor not needed for utility classes
    }

    // Create an INSERT SQL query for an object/entity
    public static String createQueryINSERT(Object object){
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(object.getClass().getSimpleName()).append(" (");

        // Get the names of all the fields of the object
        String[] fields = ObjectHelper.getFields(object);

        // Append the names of the fields to the query
        for (String field : fields) {
            sb.append(field).append(", ");
        }
        sb.setLength(sb.length() - 2); // Remove the last comma and space
        sb.append(") VALUES (");

        // Append placeholders for values to the query
        for(String key : fields) {
            sb.append("?, ");
        }
        sb.setLength(sb.length() - 2); // Remove the last comma and space
        sb.append(")");

        return sb.toString();
    }

    // Create a SELECT SQL query for an object/entity
    public static String createQuerySELECT(Object object){
        //Find the field representing the ID of the object
        String field = (String) Arrays.stream(ObjectHelper.getFields(object)).filter(x -> x.matches("(?i).*id.*")).findFirst().orElse(null);

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(object.getClass().getSimpleName());
        sb.append(" WHERE ").append(field).append(" = ?");
        return sb.toString();
    }

    // Create a SELECT SQL query to retrieve an entity by a specific attribute
    public static String createQuerySELECT2(Object object, String attribute){
        String field = (String) Arrays.stream(ObjectHelper.getFields(object)).filter(x -> x.matches("(?i).*" + attribute + ".*")).findFirst().orElse(null);

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(object.getClass().getSimpleName());
        sb.append(" WHERE ").append(field);
        sb.append(" = ?");

        return sb.toString();
    }

    // Create a SELECT SQL query to retrieve entities based on multiple attributes
    public static String createQuerySELECT3(Object object, List<String> attributes){
        List<String> fields = new ArrayList<>();

        // Get the field names for each attribute
        for (String attribute : attributes) {
            String field = (String) Arrays.stream(ObjectHelper.getFields(object)).filter(x -> x.matches("(?i).*" + attribute + ".*")).findFirst().orElse(null);
            fields.add(field);
        }

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(object.getClass().getSimpleName());
        sb.append(" WHERE ").append(fields.get(0)).append(" = ?");
        //Append conditions for additional attributes
        for (int i = 1; i < fields.size(); i++) {
            sb.append(" AND ").append(fields.get(i)).append(" = ?");
        }
        return sb.toString();
    }

    // Create an UPDATE SQL query for an object/entity
    public static String createQueryUPDATE(Object object){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(object.getClass().getSimpleName()).append(" SET ");

        // Get the names of all the fields of the object
        String[] fields = ObjectHelper.getFields(object);

        // Append fields for the SET clause
        for (String field : fields) {
            sb.append(field).append(" = ?, ");
        }
        sb.setLength(sb.length() - 2); // Remove the last comma and space
        sb.append(" WHERE ").append(ObjectHelper.getIdAttributeName(object.getClass())).append(" = ?");

        return sb.toString();
    }

    // Create a SELECT SQL query for all objects/entities of a class
    public static String createQuerySELECTAll(Class theClass){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        return sb.toString();
    }

    // Create a DELETE SQL query for an object/entity
    public static String createQueryDELETE(Object object){
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(object.getClass().getSimpleName());
        sb.append(" WHERE ").append(ObjectHelper.getIdAttributeName(object.getClass())).append(" = ?");
        return sb.toString();
    }

    public static String createQuerySELECTByUsername(Class theClass, String username){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        //sb.append(" WHERE username = ?").append(username); Este esta bien??
        sb.append("WHERE username = ?").append(username).append("?");
        return sb.toString();
    }

    public static String createQuerySELECTByUsernameAndPassword(Class theClass, String username, String password){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE username = ?").append(username);
        sb.append(" AND password = ?").append(password);
        return sb.toString();
    }
}
