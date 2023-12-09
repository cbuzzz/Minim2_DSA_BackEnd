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
    void delete(Object entity);
    Object select(Class theClass, String id, Object value);
    //List<Object> findAll(Class theClass);
    //List<Object> findAll(Class theClass, HashMap<String,String> params);
    List<Object> query(String query, Class theClass, HashMap params);
    //List<Object> getList(Class theClass, String id, Object value);
}
