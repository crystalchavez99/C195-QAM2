package com.example.qaii.database;
import com.example.qaii.DAO;
import com.example.qaii.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;


public class UserDB extends User {

    public UserDB(int user_id, String user_name,String password){
        super();
    }

    public static int loginUser(String username, String password) throws SQLException, SQLTimeoutException {
        System.out.println("ATTEMPTED TO LOGIN");
        try{
            String sql = "Select * from user where user_name='"+username+"' and password='"+password+"'";
            PreparedStatement ps = DAO.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
//            return rs.getInt("user_id");
            if(rs.getString("user_name").equals(username)){
                System.out.println("LOGGED IN");
                return rs.getInt("user_id");
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }
}
