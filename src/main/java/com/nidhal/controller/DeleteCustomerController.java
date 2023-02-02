package com.nidhal.controller;

import com.nidhal.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import static com.nidhal.model.DataBaseConnection.deleteCustomerByID;

/**
 * DeleteCustomerController class is used to delete a customer from the database
 * using the customer's ID.
 */
public class DeleteCustomerController extends BaseController {

    /**
     * Creates a new instance of DeleteCustomerController.
     *
     * @param viewFactory a ViewFactory object to create new views
     * @param fxmlName the name of the FXML file associated with this controller
     */
    public DeleteCustomerController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    /** The delete button */
    @FXML
    private Button btnDelete;

    /** The label to display messages */
    @FXML
    private Label messageLabel;

    /** The text field to input the customer's ID */
    @FXML
    private TextField textFieldForId;

    /**
     * Deletes a customer from the database using their ID.
     * Displays an error message if the ID is not 8 numbers.
     */
    @FXML
    void deleteCustomer() {
        // Parse the customer's ID
        int id = 0;
        try {
            id = Integer.parseInt(textFieldForId.getText());
        } catch (IllegalArgumentException ex) {
            messageLabel.setText("MUST BE 8 NUMBERS");
            return;
        }

        // Delete the customer from the database
        deleteCustomerByID(id, messageLabel, textFieldForId);
    }
}
