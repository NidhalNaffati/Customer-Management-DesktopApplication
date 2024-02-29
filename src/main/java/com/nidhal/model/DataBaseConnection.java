package com.nidhal.model;


import com.nidhal.config.DBConfig;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

import static com.nidhal.model.Customer.validID;

/**
 * DataBaseConnection is a class that handles connecting the database
 * and performing CRUD operations on the "customer_table" using JDBC.
 * It contains methods for creating the database and table if they do not exist, adding new customers,
 * deleting customers by ID, and getting a connection to the database.
 */
public class DataBaseConnection {

    // Logger
    private static final Logger logger = Logger.getLogger(DataBaseConnection.class.getName());

    public static Connection getDataBaseConnection() throws SQLException {
        try {
            return DriverManager.getConnection(DBConfig.getUrl(), DBConfig.getUser(), DBConfig.getPassword());
        } catch (SQLException e) {
            throw new SQLException("ERROR CONNECTING TO THE DATABASE", e);
        }
    }

    public static void createDataBaseIfNotExists() {
        try (Connection connection = getDataBaseConnection(); Statement statement = connection.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS CustomerDB";
            statement.executeUpdate(sql);
            logger.info("DATABASE CREATED SUCCESSFULLY.");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating database", e);
        }
    }

    public static void createTableCustomerIfNotExists() {
        try (Connection connection = getDataBaseConnection();
             Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS customer_table (id INT NOT NULL, firstName VARCHAR(16) NOT NULL, lastName VARCHAR(16) NOT NULL, phoneNumber INT(8) NULL, location VARCHAR(32) NOT NULL, PRIMARY KEY (id), UNIQUE INDEX phoneNumber_UNIQUE (phoneNumber))";
            statement.executeUpdate(sql);
            logger.info("TABLE CREATED SUCCESSFULLY.");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating customer table", e);
        }
    }

    public static void deleteCustomerByID(int id, Label messageLabel, TextField textFieldForId) {
        if (!validID(id)) {
            logger.warning("INVALID ID:" + id);
            messageLabel.setText("ID MUST BE A POSITIVE INTEGER");
            return;
        }

        String sql = "DELETE FROM customer_table WHERE id = ?";

        try (Connection connection = getDataBaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                messageLabel.setText("CUSTOMER DELETED SUCCESSFULLY, ID: " + id);
            } else {
                messageLabel.setText("CUSTOMER NOT FOUND, ID: " + id);
            }
            textFieldForId.clear();
        } catch (SQLException ex) {
            logger.severe("ERROR DELETING CUSTOMER: " + ex.getMessage());
            messageLabel.setText("ERROR DELETING CUSTOMER, ID: " + id);
        }
    }

    public static void addNewCustomer(int id, int phoneNumber, String firstName, String lastName, String location, Label messageLabel, TextField textFieldForId) {
        if (!validID(id)) {
            logger.warning("INVALID ID: " + id);
            messageLabel.setText("ID MUST BE A POSITIVE INTEGER");
            return;
        }

        String sql = "INSERT INTO customer_table (id, firstName, lastName, phoneNumber, location) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getDataBaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, phoneNumber);
            preparedStatement.setString(5, location);
            preparedStatement.executeUpdate();
            messageLabel.setText("CUSTOMER ADDED SUCCESSFULLY");
        } catch (SQLIntegrityConstraintViolationException e) {
            logger.warning("ID NUMBER ALREADY EXISTS: " + id);
            messageLabel.setText("ID NUMBER ALREADY EXISTS");
            textFieldForId.clear();
        } catch (SQLException e) {
            logger.severe("ERROR ADDING CUSTOMER: " + e.getMessage());
            messageLabel.setText("ERROR ADDING CUSTOMER");
        }
    }

    public static ArrayList<Customer> getAllCustomersFromTheDB() {
        ArrayList<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer_table";

        try (Connection connection = getDataBaseConnection(); Statement statement = connection.createStatement(); ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                customerList.add(new Customer(result.getInt("id"), result.getString("firstName"), result.getString("lastName"), result.getInt("phoneNumber"), result.getString("location")));
            }
        } catch (SQLException e) {
            logger.severe("ERROR RETRIEVING DATA: " + e.getMessage());
        }
        return customerList;
    }

    public static ArrayList<Customer> getCustomersById(int id) {
        ArrayList<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer_table WHERE id = ?";

        try (Connection connection = getDataBaseConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    customerList.add(new Customer(result.getInt("id"), result.getString("firstName"), result.getString("lastName"), result.getInt("phoneNumber"), result.getString("location")));
                }
            }
        } catch (SQLException e) {
            logger.severe("ERROR RETRIEVING DATA: " + e.getMessage());
        }
        return customerList;
    }
}