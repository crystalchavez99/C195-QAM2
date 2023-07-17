package com.example.qaii.controllers;

import com.example.qaii.DAO;
import com.example.qaii.database.AppointmentDB;
import com.example.qaii.models.Appointment;
import com.example.qaii.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

    ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Appointment, Integer> appointment_id;
    @FXML
    private TableColumn<Appointment, String> title;
    @FXML
    private TableColumn<Appointment, String> description;
    @FXML
    private TableColumn<Appointment, String> location;
    @FXML
    private TableColumn<Appointment, String> type;
    @FXML
    private TableColumn<Appointment, String> start;
    @FXML
    private TableColumn<Appointment, String> end;
    @FXML
    private TableColumn<Appointment, Integer> customer_id;
    @FXML
    private TableColumn<Appointment, Integer> contact_id;
    @FXML
    private TableColumn<Appointment, Integer> user_id;

    private final DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final ZoneId localZoneID = ZoneId.systemDefault();
    private final ZoneId utcZoneID = ZoneId.of("UTC");

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


    }

    public void setAppointmentTable() throws SQLException{
        PreparedStatement ps;
        try{
            ps = DAO.startCon().prepareStatement(
                    "SELECT appointment.appointment_id, appointment.customer_id, appointment.user_id, appointment.title, appointment.description, "
                            + "appointment.location, appointment.contact, appointment.type, appointment.start, appointment.end, "
                            + "appointment.created_by, customer.customer_id, customer.customer_name "
                            + "FROM appointment, customer "
                            + "WHERE appointment.customer_id = customer.customer_id "
                            + "ORDER BY `start`");
            ResultSet rs = ps.executeQuery();
            appointmentList.clear();
            while (rs.next()) {
                int appointmentID = rs.getInt("appointment_id");
                int customerID = rs.getInt("customer_id");
                int userID = rs.getInt("user_id");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String contact = rs.getString("contact");

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

                //get title from appointment
                String title = rs.getString("title");

                //get type from appointment
                String type = rs.getString("type");

                //put Customer data into Customer object
                Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("customer_name"));
                String customer_name = customer.getCustomer_name();
                System.out.println("Customer Name: " + customerName);

                String user = rs.getString("created_by");
                appointmentList.add(new Appointment(appointment_id, title, description, location, type, localStartDT, localEndDT, customer_name, user));
            }
        } catch (Exception e) {
            System.out.println("Something other than SQL has caused an error!");
        }

    }
}
