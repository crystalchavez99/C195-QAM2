package com.example.qaii.controllers;

import com.example.qaii.DAO;
import com.example.qaii.database.AppointmentDB;
import com.example.qaii.models.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class appointmentController {

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<?, ?> appointment_id;
    @FXML
    private TableColumn<?, ?> title;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> location;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> start;
    @FXML
    private TableColumn<?, ?> end;
    @FXML
    private TableColumn<?, ?> customer_id;
    @FXML
    private TableColumn<?, ?> contact_id;
    @FXML
    private TableColumn<?, ?> user_id;

    @FXML
    public void backButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() throws SQLException {
        ObservableList<Appointment> getAllAppointments = AppointmentDB.getAllAppointments();
        appointment_id.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        title.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        description.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        location.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        type.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        contact_id.setCellValueFactory(new PropertyValueFactory<>("contact_id"));
        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        appointmentTable.setItems(getAllAppointments);

        try{
            DAO.startCon();



        }catch{

        }
    }
}
