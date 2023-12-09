package edu.upc.dsa.CRUD;

import edu.upc.dsa.CRUD.util.ObjectHelper;
import edu.upc.dsa.CRUD.util.QueryHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) throws SQLException {
        String insertQuery = QueryHelper.createQueryINSERT(entity);
        PreparedStatement pstmnt = null;
        try {
            pstmnt = conn.prepareStatement(insertQuery);
            pstmnt.setObject(1, 0);
            int i = 1;
            for (String key : ObjectHelper.getFields(entity)) {
                pstmnt.setObject(i++, ObjectHelper.getter(entity, key));
            }
            pstmnt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Object select(Class theClass, String id, Object value) {
        String selectQuery = QueryHelper.createQuerySELECT(theClass, id);
        ResultSet rs;
        PreparedStatement pstmnt = null;
        boolean empty = true;

        try {
            pstmnt = conn.prepareStatement(selectQuery);
            pstmnt.setObject(1, value);
            rs = pstmnt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            Object obj = theClass.newInstance();
            Object valueObj = null;
            while (rs.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    String columnName = rsmd.getColumnName(i);
                    ObjectHelper.setter(obj, columnName, rs.getObject(columnName));
                    System.out.println(columnName);
                    System.out.println(rs.getObject(i));
                    //valueColumn = rs.getObject(i);
                }
            }
            return obj;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public void update(Object object) throws SQLException {
        String updateQuery = QueryHelper.createQueryUPDATE(object.getClass(), "PASSWORD", "ID");
        PreparedStatement statement = conn.prepareStatement(updateQuery);
        int i = 1;
        for (String key : ObjectHelper.getFields(object)) {
            statement.setObject(i++, ObjectHelper.getter(object, key));
        }
        statement.setObject(i, ObjectHelper.getter(object, ObjectHelper.getFields(object)[0]));
        statement.executeQuery();
    }

    public void delete(Object object){

    }

    @Override
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }



}
