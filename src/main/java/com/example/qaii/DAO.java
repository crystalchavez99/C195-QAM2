package com.example.qaii;

import java.sql.*;

public class DAO {
    public static Connection conn;
    private static PreparedStatement preparedStatement;

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//localhost:3306/client_schedule";
    private static final String url = protocol + vendorName + ipAddress;

    private static final String username = "root";
    private static final String password = "password";

    public static Connection startCon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
            conn = (Connection) DriverManager.getConnection(url, username, password);
        }catch (Exception e) {
            throw new RuntimeException("uncaught", e);
        }
//        System.out.println("CONN", con);
        return conn;
    }

    /**
     * Method to get the current connection.
     * @return current connection.
     */
    public static Connection getConnection() throws SQLException, SQLTimeoutException {
        return conn;
    }

    /**
     * Close the connection.
     */
    public static void closeCon() {
        try {
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException("uncaught", e);
        }
    }

    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        preparedStatement = conn.prepareStatement(sqlStatement);
    }

    public static PreparedStatement getPreparedStatement() {

        return preparedStatement;
    }
}
