package com.nidhal.controller;

import com.nidhal.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * LoginPageController class is responsible for controlling the functionality of the login page.
 * It has several buttons for different functionalities like adding, viewing all customers, deleting, etc.
 * The class extends the BaseController class.
 */
public class LoginPageController extends BaseController {

    /**
     * Creates a new instance of the LoginPageController class.
     *
     * @param viewFactory an instance of the ViewFactory class that is used to create the views.
     * @param fxmlName the name of the FXML file that corresponds to this controller.
     */
    public LoginPageController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private Button btnAdd, btnAllData, btnDelete, btnModify, btnOption;

    /**
     * getAllCustomersFromTheDB() method is used to display all the customer's data.
     */
    @FXML
    void getAllCustomersFromTheDB() {
        viewFactory.showAllDataWindow();
    }

    /**
     * addCustomer() method is used to open the window for adding a customer.
     */
    @FXML
    void addCustomer() {
        viewFactory.showAddCustomerWindow();
    }

    /**
     * option() method is used to open the option window.
     */
    @FXML
    void option() {
        viewFactory.showOptionWindow();
    }

    /**
     * deleteCustomerByID() method is used to open the window for deleting a customer by its ID.
     */
    @FXML
    void deleteCustomerByID() {
        viewFactory.showDeleteCustomerWindow();
    }
}
