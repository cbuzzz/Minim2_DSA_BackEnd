package edu.upc.dsa.CRUD.MYSQL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

public interface Session {
    void save(Object entity) throws SQLException;
    void close();
    void clean();
    Object get(Class theClass, String id);
    Object get(Class theClass, String attribute, String value) throws SQLException;
    void update(Object object) throws SQLException;
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap<String,String> params) throws SQLException;
    List<Object> query(String query, Class theClass, HashMap params);
}
