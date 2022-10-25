module Customer.Management.DesktopApplication {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;


    opens com.nidhal;
    opens com.nidhal.model;
    opens com.nidhal.controller;
    opens com.nidhal.view;

}