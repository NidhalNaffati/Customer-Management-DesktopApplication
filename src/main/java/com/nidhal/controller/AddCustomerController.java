package com.nidhal.controller;


import com.nidhal.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import static com.nidhal.model.DataBaseConnection.addNewCustomer;
import static com.nidhal.model.Customer.validFields;

/**
 * The `AddCustomerController` class is a JavaFX controller for the "Add Customer" screen.
 * It extends the `BaseController` class and is responsible for adding new customers to the database.
 */
public class AddCustomerController extends BaseController {
    /**
     * Constructor to initialize the `AddCustomerController` class.
     * It takes in two arguments: a `ViewFactory` object and a `String` containing the FXML file name.
     *
     * @param viewFactory the `ViewFactory` object used to switch between screens.
     * @param fxmlName    the `String` containing the name of the FXML file.
     */
    public AddCustomerController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    /**
     * JavaFX `Button` object that triggers the `addCustomer()` method when clicked.
     */
    @FXML
    private Button btnAdd;

    /**
     * JavaFX `Label` objects for displaying messages to the user.
     */
    @FXML
    private Label messageLabel, idLabel, firstNameLabel, locationLabel, phoneNumberLabel, scenceLabel, secondNameLabel;

    /**
     * JavaFX `TextField` objects for entering customer information.
     */
    @FXML
    private TextField textFieldForId, textFieldForFirstName, textFieldForLocation,
            textFieldForPhoneNumber, textFieldForLastName;

    /**
     * Method to add a new customer to the database.
     * <p>
     * The method retrieves the customer information from the text fields and checks if the values are valid.
     * If the values are invalid, it displays a message to the user and clears the text fields.
     * If the values are valid, it calls the `addNewCustomer` method and passes in the customer information,
     * along with the message label and the text field for the ID.
     */
    @FXML
    void addCustomer() {

        // Retrieve the customer information from the text fields
        String firstName = textFieldForFirstName.getText();
        String lastName = textFieldForLastName.getText();
        String location = textFieldForLocation.getText();

        // Convert the ID from a string to an int
        int id = 0;
        try {
            id = Integer.parseInt(textFieldForId.getText());
        } catch (IllegalArgumentException ex) {
            messageLabel.setText("ID must be 8 numbers.");
        }

        // Convert the phone number from a string to an int
        int phoneNumber = 0;
        try {
            phoneNumber = Integer.parseInt(textFieldForId.getText());
        } catch (IllegalArgumentException ex) {
            messageLabel.setText("Phone number must be 8 numbers.");
        }

        // Check if the values are valid
        if (!validFields(id, phoneNumber, firstName, lastName, location)) {
            messageLabel.setText("wrong values check out the docs.");
            textFieldForId.clear();
        } else {
            addNewCustomer(id, phoneNumber, firstName, lastName, location, messageLabel, textFieldForId);
        }
    }

    private void clearTextFields() {
        textFieldForFirstName.clear();
        textFieldForLastName.clear();
        textFieldForId.clear();
        textFieldForLocation.clear();
        textFieldForPhoneNumber.clear();
    }
}