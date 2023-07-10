package com.example.qaii.controllers;

import com.example.qaii.Main;
import com.example.qaii.database.AppointmentDB;
import com.example.qaii.models.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class homeController {

    @FXML
    private Button logoutButton;

    @FXML
    private Text notification;


    public void initialize() throws SQLException  {
        try{
            ObservableList<Appointment> getAllAppointments = AppointmentDB.getAllAppointments();
            LocalDateTime before15Min = LocalDateTime.now().minusMinutes(15);
            LocalDateTime after15Min = LocalDateTime.now().plusMinutes(15);
            LocalDateTime time = null;
            int appointment_id = 0;
            LocalDateTime displayTime = null;
            boolean appointmentRange = false;

            for(Appointment appointment: getAllAppointments){
                time = appointment.getStart();
                if(time.isAfter(before15Min) || time.isEqual(before15Min)){
                    if(time.isBefore(after15Min) || time.isEqual(after15Min)){
                        appointment_id = appointment.getAppointmentId();
                        displayTime = time;
                        appointmentRange = true;
                    }
                }
            }
            if(appointmentRange){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment within 15 minutes: " + appointment_id + " and appointment start time of: " + displayTime);
                alert.showAndWait();
                notification.setText("There is an appointment within 15 minutes");
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No upcoming appointments.");
                alert.showAndWait();
                notification.setText("There is no upcoming appointments!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void logoutButton(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void openAppointments(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("appointmentView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    public void openContacts(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contactView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Contacts");
        stage.setScene(scene);
        stage.show();
    }


}
