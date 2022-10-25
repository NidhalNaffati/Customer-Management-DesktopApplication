package com.nidhal.controller;

import com.nidhal.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import static com.nidhal.model.DataBaseConnection.deleteCustomerByID;

public class DeleteCustomerController extends BaseController {

    public DeleteCustomerController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private Button btnDelete;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField textFieldForId;


    @FXML
    void deleteCustomer() {

        //checking the id before we create our sql statement.
        int ID = 0;
        try {
            ID = Integer.parseInt(textFieldForId.getText());

        } catch (IllegalArgumentException ex) {
            messageLabel.setText("MUST BE 8 NUMBERS");
        }

        deleteCustomerByID(ID, messageLabel, textFieldForId);
    }
}

