package com.nidhal;

import com.nidhal.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;


import static com.nidhal.model.DataBaseConnection.createDataBaseIfNotExists;
import static com.nidhal.model.DataBaseConnection.createTableCustomerIfNotExists;

public class Launcher extends Application {


    public static void main(String[] args) {

        createDataBaseIfNotExists();

        createTableCustomerIfNotExists();

        launch();
    }

    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showLoginWindow();
    }


}
