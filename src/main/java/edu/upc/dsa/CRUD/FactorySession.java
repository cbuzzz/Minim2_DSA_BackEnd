package edu.upc.dsa.CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FactorySession {
    public static Session openSession() {
        Connection conn = getConnection();
        Session session = new SessionImpl(conn);
        return session;
    }

    private static Connection getConnection(){
        Connection conn = null;
        try {
            // AQUESTA URL ES LA BONA???
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbdd_juego_2023", "Nani", "1234");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO items (name, type, price, level) VALUES ('Espada', 'Arma', 10, 1)");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
