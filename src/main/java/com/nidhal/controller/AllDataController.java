package com.nidhal.controller;


import com.nidhal.model.DataBaseConnection;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class AllDataController extends BaseController implements Initializable {

    public AllDataController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private TableView<Customer> tableViewForCustomer;


    @FXML
    private TableColumn<Customer, Integer> idCall;

    @FXML
    private TableColumn<Customer, String> firstNameCall;

    @FXML
    private TableColumn<Customer, String> lastNameCall;

    @FXML
    private TableColumn<Customer, String> locationCall;

    @FXML
    private TableColumn<Customer, Integer> phoneNumberCall;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField textFieldForSearch;


    ObservableList<Customer> customerList = FXCollections.observableArrayList();


    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;


    // get all customers from the database.

    public void getAllCustomersFromTheDB() {


        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        ObservableList<Customer> customerList = FXCollections.observableArrayList();


        try {
            connection = DataBaseConnection.getDataBaseConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("select * from customer_table ");

            while (result.next()) {
                customerList.add(new Customer(
                        result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getInt("phoneNumber"),
                        result.getString("location")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        idCall.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCall.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCall.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumberCall.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        locationCall.setCellValueFactory(new PropertyValueFactory<>("location"));

        tableViewForCustomer.setItems(customerList);
    }


    @FXML
    void refreshData() {
        customerList.clear();
        textFieldForSearch.clear();
        getAllCustomersFromTheDB();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllCustomersFromTheDB();
    }


    @FXML
    void searchForCustomerByID() {
        int idNumber = 0;
        try {
            idNumber = Integer.parseInt(textFieldForSearch.getText());
        } catch (NumberFormatException ex) {
        }

        try {
            connection = DataBaseConnection.getDataBaseConnection();

            statement = connection.createStatement();

             result = statement.executeQuery("select * from customer_table where id =  " + idNumber);

            customerList.clear();

            while (result.next()) {
                customerList.add(new Customer(
                        result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getInt("phoneNumber"),
                        result.getString("location")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        idCall.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCall.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCall.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumberCall.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        locationCall.setCellValueFactory(new PropertyValueFactory<>("location"));
        tableViewForCustomer.setItems(customerList);

    }
}
