package com.example.qaii;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO {
    public static Connection con = null;

    public static Connection startCon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule","root","password");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }

    /**
     * Method to get the current connection.
     * @return current connection.
     */
    public static Connection getCon() {
        return con;
    }

    /**
     * Close the connection.
     */
    public static void closeCon() {
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
