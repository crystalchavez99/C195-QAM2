module com.example.qaii {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.qaii to javafx.fxml;
    exports com.example.qaii;
    exports com.example.qaii.controllers;
    opens com.example.qaii.controllers to javafx.fxml;
}