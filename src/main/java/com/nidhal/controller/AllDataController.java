package com.nidhal.controller;

import com.nidhal.model.Customer;
import com.nidhal.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.nidhal.model.DataBaseConnection.getAllCustomersFromTheDB;
import static com.nidhal.model.DataBaseConnection.getCustomersById;

/**
 * The AllDataController class extends the BaseController and implements the Initializable interface.
 * It is responsible for displaying all customer data in a table and for searching for a specific customer by ID.
 *
 */
public class AllDataController extends BaseController implements Initializable {

    /**
     * Constructor for AllDataController.
     *
     * @param viewFactory the factory to create views.
     * @param fxmlName the name of the FXML file.
     */
    public AllDataController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    /**
     * The table view to display the customer data.
     */
    @FXML
    private TableView<Customer> tableViewForCustomer;

    /**
     * The table columns for displaying the customer ID, phone number, first name, last name, and location.
     */
    @FXML
    private TableColumn<Customer, Integer> idCall, phoneNumberCall;
    @FXML
    private TableColumn<Customer, String> firstNameCall , lastNameCall, locationCall;

    /**
     * The button for triggering the search.
     */
    @FXML
    private Button btnSearch;

    /**
     * The text field for entering the search criteria.
     */
    @FXML
    private TextField textFieldForSearch;

    /**
     * The list to store the customer data.
     */
    ObservableList<Customer> customerList = FXCollections.observableArrayList();


    /**
     * The initialize method is called when the view is loaded. It shows all the customers.
     *
     * @param url the URL of the resource.
     * @param resourceBundle the resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showUpAllCustomers();
    }


    /**
     * Refreshes the customer data by clearing the current data and calling the showAllCustomers method.
     */
    @FXML
    void refreshData() {
        customerList.clear();
        textFieldForSearch.clear();
        showUpAllCustomers();
    }

    /**
     * Shows all the customers by retrieving the data from the database and setting it to the table view.
     */
    @FXML
    private void showUpAllCustomers() {

        ArrayList<Customer> customerArrayList =  getAllCustomersFromTheDB();

        ObservableList<Customer> observableArrayList = FXCollections.observableList(customerArrayList);

        SetCustomers(observableArrayList);

    }

    /**
     * Searches for a customer by ID by retrieving the data from the database and setting it to the table view.
     */
    @FXML
    private void searchForCustomerByID() {
        int idNumber = 0;
        try {
            idNumber = Integer.parseInt(textFieldForSearch.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        ArrayList<Customer> customerArrayList =  getCustomersById(idNumber);

        ObservableList<Customer> observableArrayList = FXCollections.observableList(customerArrayList);

        SetCustomers(observableArrayList);

    }

    /**
     * This method sets the customers to be displayed in the table view for customers.
     *
     * @param customerList - An ObservableList of Customer objects.
     */
    private void SetCustomers(ObservableList<Customer> customerList) {
        idCall.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCall.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCall.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumberCall.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        locationCall.setCellValueFactory(new PropertyValueFactory<>("location"));

        tableViewForCustomer.setItems(customerList);
    }
}
