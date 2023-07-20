package com.example.qaii.controllers;

import com.example.qaii.Main;
import com.example.qaii.database.AppointmentDB;
import com.example.qaii.models.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.sql.SQLException;

public class appointmentController {

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, String> appointment_idColumn;
@FXML
private TableColumn<Appointment, String> titleColumn;
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
@FXML
private TableColumn<Appointment, String> locationColumn;

ObservableList<Appointment> getAllAppointments = AppointmentDB.getAllAppointments();

    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private TableColumn<Appointment, String> startColumn;
    @FXML
    private TableColumn<Appointment, String> endColumn;
    @FXML
    private TableColumn<Appointment, String> customer_idColumn;
    @FXML
    private TableColumn<Appointment, String> contactColumn;
    @FXML
    private TableColumn<Appointment, String> user_idColumn;

    public appointmentController() throws SQLException {
    }
//
//    private final DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    private final ZoneId localZoneID = ZoneId.systemDefault();
//    private final ZoneId utcZoneID = ZoneId.of("UTC");

    @FXML
    public void backButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() throws SQLException {

        PropertyValueFactory<Appointment, String> apptId= new PropertyValueFactory<>("Appointment Id");
        appointment_idColumn.setCellValueFactory(apptId);
        PropertyValueFactory<Appointment, String> apptTitle= new PropertyValueFactory<>("Title");
        titleColumn.setCellValueFactory(apptTitle);
        PropertyValueFactory<Appointment, String> apptDescription= new PropertyValueFactory<>("Description");
        descriptionColumn.setCellValueFactory(apptDescription);
        PropertyValueFactory<Appointment, String> apptLocation = new PropertyValueFactory<>("Location");
        locationColumn.setCellValueFactory(apptLocation);
        PropertyValueFactory<Appointment, String> apptType = new PropertyValueFactory<>("Type");
        typeColumn.setCellValueFactory(apptType);
        PropertyValueFactory<Appointment, String> apptStart = new PropertyValueFactory<>("Start");
        startColumn.setCellValueFactory(apptStart);
        PropertyValueFactory<Appointment, String> apptEnd = new PropertyValueFactory<>("End");
        endColumn.setCellValueFactory(apptEnd);
        PropertyValueFactory<Appointment, String> apptCustomerId = new PropertyValueFactory<>("Customer ID");
        customer_idColumn.setCellValueFactory(apptCustomerId);
        PropertyValueFactory<Appointment, String> apptContact= new PropertyValueFactory<>("Contact");
        contactColumn.setCellValueFactory(apptContact);
        PropertyValueFactory<Appointment, String> apptUserId= new PropertyValueFactory<>("User ID");
        user_idColumn.setCellValueFactory(apptUserId);

    }

    @FXML
    public void addApointment(ActionEvent event) throws IOException {
        Parent addParts = FXMLLoader.load(Main.class.getResource("addAppointmentView.fxml"));
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
