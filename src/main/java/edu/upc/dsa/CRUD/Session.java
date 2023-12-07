package edu.upc.dsa.CRUD;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

public interface Session<E> {
    void save(Object entity) throws SQLException;
    void close();
    Object get(Class theClass, String id, Object value);
    void delete(Object entity) throws SQLException;
    void update(Object entity) throws SQLException;
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap<String,String> params) throws SQLException;
    List<Object> query(String query, Class theClass, Map params);
    List<Object> getList(Class theClass, String id, Object value);
}
