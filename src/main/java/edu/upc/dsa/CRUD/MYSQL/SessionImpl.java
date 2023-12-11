package edu.upc.dsa.CRUD.MYSQL;

import edu.upc.dsa.CRUD.util.ObjectHelper;
import edu.upc.dsa.CRUD.util.QueryHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    // Save an entity to the database
    @Override
    public void save(Object entity) throws SQLException {
        try{
            // Generate the INSERT query dynamically
            String insertQuery = QueryHelper.createQueryINSERT(entity);

            // Create a prepared statement from the query
            PreparedStatement pstmnt = conn.prepareStatement(insertQuery);
            int i = 1;
            for(String field : ObjectHelper.getFields(entity)){
                // Get the value of the field
                Object value = ObjectHelper.getter(entity, field);
                // Set the value of the field in the prepared statement
                pstmnt.setObject(i, value);
                i++;
            }
            // Execute the query and save the entity
            pstmnt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Close the database connection
    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Clean the database connection
    public void clean() {
        try {
            this.conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get an entity from the database by its ID
    @Override
    public Object get(Class theClass, String id) {
        try{
            // Create a new instance of the object/entity
            Object object = theClass.newInstance();
            ObjectHelper.setter(object, ObjectHelper.getIdAttributeName(theClass), id);

            // Generate the SELECT query dynamically
            String selectQuery = QueryHelper.createQuerySELECT(object);
            PreparedStatement pstmnt = this.conn.prepareStatement(selectQuery);
            pstmnt.setObject(1, id);

            // Retrieve and return the object/entity
            object = ObjectHelper.createObjects(pstmnt.executeQuery(), theClass).get(0);
            assert object != null;
            return object;
        }catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
    }

    // Get an entity from the database by a specific attribute and its value
    @Override
    public Object get(Class theClass, String attribute, String value){
        try{
            // Create a new instance of the object/entity
            Object object = theClass.newInstance();
            ObjectHelper.setter(object, ObjectHelper.getAttributeName(theClass, attribute), value);

            // Generate the SELECT query dynamically
            String selectQuery = QueryHelper.createQuerySELECT2(object, attribute);
            PreparedStatement statement = this.conn.prepareStatement(selectQuery);
            statement.setObject(1, value);

            // Retrieve and return the object/entity if found, otherwise throw an exception
            List<Object> objects = ObjectHelper.createObjects(statement.executeQuery(), theClass);
            if(!objects.isEmpty()) {
                object = objects.get(0);
                return object;
            }else{
                throw new SQLException("No object/entity found with " + attribute + " = " + value);
            }
        }catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
    }

    // Update an existing entity/object in the database
    @Override
    public void update(Object object) throws SQLException{
        try{
            // Generate the UPDATE query dynamically
            String updateQuery = QueryHelper.createQueryUPDATE(object);
            PreparedStatement pstmnt = this.conn.prepareStatement(updateQuery);
            int i = 1;
            for(String field : ObjectHelper.getFields(object)){
                // Get the value of the field
                Object value = ObjectHelper.getter(object, field);
                // Set the value of the field in the prepared statement
                pstmnt.setObject(i, value);
                i++;
            }
            // Execute the query and update the entity/object
            pstmnt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Delete an entity/object from the database
    @Override
    public void delete(Object object) {
        try{
            // Generate the DELETE query dynamically
            String deleteQuery = QueryHelper.createQueryDELETE(object);
            PreparedStatement pstmnt = this.conn.prepareStatement(deleteQuery);
            pstmnt.setObject(1, ObjectHelper.getter(object, ObjectHelper.getIdAttributeName(object.getClass())));
            // Execute the query and delete the entity/object
            pstmnt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Retrieve all entities/objects of a specific class from the database
    @Override
    public List<Object> findAll(Class theClass) {
        String selectQuery = QueryHelper.createQuerySELECTAll(theClass);
        List<Object> objects = null;
        try{
            // Excute the SELECT query to retrieve all entities
            PreparedStatement pstmnt = this.conn.prepareStatement(selectQuery);
            objects = ObjectHelper.createObjects(pstmnt.executeQuery(), theClass);
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return objects;
    }

    // Retrieve entities of a specific class based on the provided parameters
    @Override
    public List<Object> findAll(Class theClass, HashMap<String,String> params) throws SQLException {
        List<Object> objects = null;
        try {
            List<String> attributes = (List<String>) params.keySet();
            List<String> values = (List<String>) params.values();
            Object object = theClass.newInstance();
            // Set entity attributes based on the provided parameters
            for (int i = 0; i < params.size(); i++) {
                ObjectHelper.setter(object, ObjectHelper.getAttributeName(theClass, attributes.get(i)), values.get(i));
            }
            // Generate the SELECT query dynamically
            String selectQuery = QueryHelper.createQuerySELECT3(object, attributes);
            PreparedStatement pstmnt = this.conn.prepareStatement(selectQuery);

            // Set the values of the parameters in the prepared statement
            for (int i = 0; i < params.size(); i++) {
                pstmnt.setObject(i+1, values.get(i));
            }

            // Execute the query and retrieve the entities
            objects = ObjectHelper.createObjects(pstmnt.executeQuery(), theClass);
            return objects;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    // Perform a custom query on the database
    public List<Object> query(String query, Class theClass, HashMap params){
        return null;
    }
}
