package com.nidhal.controller;


import com.nidhal.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import static com.nidhal.model.DataBaseConnection.addNewCustomer;
import static com.nidhal.model.Customer.validFields;


public class AddCustomerController extends BaseController {

    public AddCustomerController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private Button btnAdd;

    @FXML
    private Label messageLabel, idLabel, firstNameLabel,
            locationLabel, phoneNumberLabel, scenceLabel, secondNameLabel;

    @FXML
    private TextField textFieldForId, textFieldForFirstName, textFieldForLocation,
            textFieldForPhoneNumber, textFieldForLastName;


    @FXML
    void addCustomer() {

        String firstName = textFieldForFirstName.getText();


        String lastName = textFieldForLastName.getText();


        String location = textFieldForLocation.getText();


        int id = 0;
        try {
            id = Integer.parseInt(textFieldForId.getText());

        } catch (IllegalArgumentException ex) {
            messageLabel.setText("MUST BE 8 NUMBERS");
        }


        int phoneNumber = 0;
        try {
            phoneNumber = Integer.parseInt(textFieldForId.getText());

        } catch (IllegalArgumentException ex) {
            messageLabel.setText("MUST BE 8 NUMBERS");
        }


        if (!validFields(id, phoneNumber, firstName, lastName, location)) {
            messageLabel.setText("WRONG VALUES CHECK OUT THE DOCUMENTATION.");
            textFieldForFirstName.clear();
            textFieldForLastName.clear();
            textFieldForId.clear();
            textFieldForLocation.clear();
            textFieldForPhoneNumber.clear();
        } else {
            addNewCustomer(id, phoneNumber, firstName, lastName, location, messageLabel, textFieldForId);
        }
    }

}