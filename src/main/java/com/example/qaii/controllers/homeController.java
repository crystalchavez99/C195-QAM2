package com.example.qaii.controllers;

import com.example.qaii.Main;
import com.example.qaii.database.AppointmentDB;
import com.example.qaii.models.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class homeController {

    @FXML
    private Button logoutButton;

    public void initialize() throws SQLException, IOException, Exception  {
        try{
            ObservableList<Appointment> getAllAppointments = AppointmentDB.getAllAppointments();
            LocalDateTime before15Min = LocalDateTime.now().minusMinutes(15);
            LocalDateTime after15Min = LocalDateTime.now().plusMinutes(15);

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
