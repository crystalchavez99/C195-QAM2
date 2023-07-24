package com.example.qaii.controllers;

import com.example.qaii.DAO;
import com.example.qaii.Main;
import com.example.qaii.database.AppointmentDB;
import com.example.qaii.models.Appointment;
import com.example.qaii.models.Customer;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
    //private User currentUser;
    private DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");
    private ZoneId localZoneID = ZoneId.systemDefault();
    private ZoneId utcZoneID = ZoneId.of("UTC");

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

        try {
            loadAppointment();
        } catch (SQLException ex) {
            System.out.println("SQL error when 'setAppointmentTable' was called.");
        }

    }

    @FXML
    public void addApointment(ActionEvent event) throws IOException {
        Parent addParts = FXMLLoader.load(Main.class.getResource("addAppointmentView.fxml"));
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void loadAppointment() throws SQLException {
        PreparedStatement ps;
        try{
            ps = DAO.startCon().prepareStatement(
            "SELECT * FROM appointment, customer"
            );
            ResultSet rs = ps.executeQuery();
            getAllAppointments.clear();
            while(rs.next()){
                int appointmentID = rs.getInt("appointment_id");
                int customerID = rs.getInt("customer_id");
                int userID = rs.getInt("user_id");
                String description = rs.getString("description");
                String location = rs.getString("location");
                Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("customer_name"));
//                String contact = rs.getString("contact");
                String contact = customer.getCustomer_name();
                String title = rs.getString("title");
                String type = rs.getString("type");
                //get database start time stored as UTC
                String startUTC = rs.getString("start").substring(0, 19);

                //get database end time stored as UTC
                String endUTC = rs.getString("end").substring(0, 19);

                //convert database UTC to LocalDateTime
                LocalDateTime utcStartDT = LocalDateTime.parse(startUTC, datetimeDTF);
                LocalDateTime utcEndDT = LocalDateTime.parse(endUTC, datetimeDTF);

                //convert times UTC zoneId to local zoneId

                ZonedDateTime localZoneStart = utcStartDT.atZone(utcZoneID).withZoneSameInstant(localZoneID);
                ZonedDateTime localZoneEnd = utcEndDT.atZone(utcZoneID).withZoneSameInstant(localZoneID);

                //convert ZonedDateTime to a string for insertion into AppointmentsTableView
                String localStartDT = localZoneStart.format(datetimeDTF);
                String localEndDT = localZoneEnd.format(datetimeDTF);
                getAllAppointments.add(new Appointment(appointmentID, customerID, userID, title, description, location, contact, type, localStartDT, localEndDT));
            }
        }catch (Exception e) {
            System.out.println(e);
        }

    }

}
