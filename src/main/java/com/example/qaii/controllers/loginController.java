package com.example.qaii.controllers;
import com.example.qaii.DAO;
import com.example.qaii.Main;
import com.example.qaii.database.UserDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.example.qaii.models.User;

import java.io.IOException;
import java.sql.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;
import java.time.ZoneId;
import java.net.URL;
import java.util.ResourceBundle;


/**
 *
 * @author Redfire
 */
public class loginController implements Initializable{
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button resetButton;
    @FXML
    private Label timezoneField;
    @FXML
    private Label timezoneLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private Label usernameLabel;
    Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Locale locale = Locale.getDefault();
            Locale.setDefault(locale);
            ZoneId zone = ZoneId.systemDefault();

            timezoneField.setText(String.valueOf(zone));

            rb = ResourceBundle.getBundle("languages/login", Locale.getDefault());
            usernameField.setText(rb.getString("username"));
            passwordField.setText(rb.getString("password"));
            loginButton.setText(rb.getString("login"));
            resetButton.setText(rb.getString("reset"));
            timezoneLabel.setText(rb.getString("location"));
        }catch(MissingResourceException e) {
            System.out.println("Resource file missing: " + e);
        }
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver loaded!");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_schedule","root","password");
//            String username = usernameField.getText();
//            String password = passwordField.getText();
//
//            Statement stm = con.createStatement();
//            String sql = "Select * from user where user_name='"+username+"' and Password='"+password+"'";
//
//
//            ResultSet rs = stm.executeQuery(sql);
//            System.out.println("Status: " + rs);
//
//
//            if(rs.next()){
////                int userId = rs.getInt("user_id");
//                FXMLLoader homePage = new FXMLLoader(Main.class.getResource("homeView.fxml"));
//                Parent homeP= homePage.load();
//                Scene homeScene = new Scene(homeP);
//                Stage homeStage = new Stage();
//                homeStage.setScene(homeScene);
//                homeStage.show();
//            }else{
////                JOptionPane.showMessageDialog(this, "username or password is wrong...");
//                Alert invalid = new Alert(Alert.AlertType.WARNING);
//                usernameField.setText("");
//                passwordField.setText("");
//            }
//            con.close();
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
    }

    @FXML
    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void resetButton(ActionEvent event) {
        usernameField.setText("");
        passwordField.setText("");
    }

    @FXML
    public void loginButton(ActionEvent event) throws IOException {
        try{
            System.out.println("CLICKED");
            String username = usernameField.getText();
            String password = passwordField.getText();
            int userId = UserDB.loginUser(username,password);
            if(userId >= 0){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("homeView.fxml"));
                Parent root = loader.load();
                stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
//        try{
//            String sql = "Select * from user where user_name='"+username+"' and password='"+password+"'";
//            PreparedStatement ps = DAO.getCon().prepareStatement(sql);
//            ResultSet rs = ps.executeQuery(sql);
//            System.out.println("Status: " + rs);
//        }catch  (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

}