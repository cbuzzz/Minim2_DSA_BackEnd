package edu.upc.dsa.CRUD.util;

import java.util.HashMap;
import java.util.Objects;

public class QueryHelper {

    public static String createQueryINSERT(Object object){
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(object.getClass().getSimpleName()).append(" (");

        //String[] fields = ObjectHelper.getFields(object);

        for (String key : ObjectHelper.getFields(object)) {
            sb.append(key).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(") VALUES (");
        for (String key : ObjectHelper.getFields(object)) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }

    public static String createQuerySELECT(Class theClass, String id){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE ").append(id).append(" = ?");
        //sb.append(" WHERE " + id + " = ?"); igual que la linea de arriba??
        return sb.toString();
    }

    public static String createQueryUPDATE(Class theClass, String SET, String WHERE){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(theClass.getSimpleName());
        if(Objects.equals(SET, "PASSWORD")){
            sb.append(" SET ").append(SET).append(" = ?").append(" WHERE ").append(WHERE).append(" = ?");
        }else{
            sb.append(" SET ").append(SET).append(" = ?").append(" WHERE ").append(WHERE).append(" = ?");
        }
        return sb.toString();
    }

    public static String createQuerySELECTAll(Class theClass){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
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
