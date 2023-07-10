package com.example.qaii.database;

import com.example.qaii.DAO;
import com.example.qaii.models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentDB {

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * from appointment";
        PreparedStatement ps = DAO.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointment_id = rs.getInt("appointment_id");
            String appointment_title = rs.getString("title");
            String appointment_description = rs.getString("description");
            String appointment_location = rs.getString("location");
            String appointment_type = rs.getString("type");
            LocalDateTime start = rs.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("end").toLocalDateTime();
            int customer_id = rs.getInt("customer_id");
            int user_id = rs.getInt("User_id");
            int contact_id = rs.getInt("Contact_id");
        }
        return allAppointments;
    }
}
