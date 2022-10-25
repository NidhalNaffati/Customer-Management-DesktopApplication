package com.nidhal.controller;

import com.nidhal.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class LoginPageController extends BaseController {

    public LoginPageController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private Button btnAdd, btnAllData, btnDelete,
            btnModify, btnOption;


    @FXML
    void getAllCustomersFromTheDB() { viewFactory.showAllDataWindow();}

    @FXML
    void addCustomer() {
        viewFactory.showAddCustomerWindow();
    }

    @FXML
    void option() {
        viewFactory.showOptionWindow();
    }

    @FXML
    void deleteCustomerByID() {
        viewFactory.showDeleteCustomerWindow();
    }


}


