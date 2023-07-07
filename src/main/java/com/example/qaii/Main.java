package com.example.qaii;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Software 2!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        DAO.startCon();
        launch();
        DAO.closeCon();
    }
}