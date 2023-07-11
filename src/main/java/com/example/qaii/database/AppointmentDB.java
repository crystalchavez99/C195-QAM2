package com.example.qaii.database;

import com.example.qaii.DAO;
import com.example.qaii.models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
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
            int user_id = rs.getInt("user_id");
            int contact_id = rs.getInt("contact_id");
            int division_id = rs.getInt("division_id");
            Appointment appointment = new Appointment(appointment_id, appointment_title, appointment_description, appointment_location, appointment_type, start, end, customer_id, user_id, contact_id, division_id);
            allAppointments.add(appointment);
        }
        System.out.println("All appointments:" + allAppointments);
        return allAppointments;
    }

    public static int deleteAppointment(int appointment_id, Connection connection) throws SQLException{
        String query = "DELETE FROM appointment WHERE appointment_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(0, appointment_id);
        int removed = ps.executeUpdate();
        ps.close();
        return removed;
    }
}
