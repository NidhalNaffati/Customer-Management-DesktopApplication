package com.nidhal.model;


import com.nidhal.config.DBConfig;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;

import static com.nidhal.model.Customer.validID;

/**
 * DataBaseConnection is a class that handles connecting to a MySQL database named "CustomerDB"
 * and performing CRUD operations on the "customer_table" using JDBC.
 * It contains methods for creating the database and table if they do not exist, adding new customers,
 * deleting customers by ID, and getting a connection to the database.
 */
public class DataBaseConnection {

    static Connection connection = null;
    static Statement statement = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet result = null;


    /**
     * Method to get a connection to the database.
     *
     * @return Connection object to the database
     * @throws SQLException if there is an error connecting to the database
     */
    public static Connection getDataBaseConnection() throws SQLException {
        try {
            Connection databaseConnection = DriverManager.getConnection(DBConfig.getUrl(), DBConfig.getUser(), DBConfig.getPassword());
            return databaseConnection;
        } catch (SQLException e) {
            throw new SQLException("ERROR CONNECTING TO THE DATABASE", e);
        }
    }
    public static void closeConnection(Connection connection, Statement statement, ResultSet result) {
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
    public static void closeConnection(Connection connection, Statement statement) {
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


    /**
     * Method to create the database if it does not exist.
     */
    public static void createDataBaseIfNotExists() {


        try {
            connection = DataBaseConnection.getDataBaseConnection();
            statement = connection.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS CustomerDB";
            statement.executeUpdate(sql);
            System.out.println("DATABASE CREATED SUCCESSFULLY.");
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeConnection(connection, statement);
        }
    }

    /**
     * Method to create the customer_table if it does not exist.
     */
    public static void createTableCustomerIfNotExists() {

        try {
            connection = DataBaseConnection.getDataBaseConnection();
            statement = connection.createStatement();

            // Use a prepared statement to make the code more readable and less prone to SQL injection attacks
            String sql = "CREATE TABLE IF NOT EXISTS customer_table (id INT NOT NULL, firstName VARCHAR(16) NOT NULL, lastName VARCHAR(16) NOT NULL, phoneNumber INT(8) NULL, location VARCHAR(32) NOT NULL, PRIMARY KEY (id), UNIQUE INDEX phoneNumber_UNIQUE (phoneNumber));";

            statement.executeUpdate(sql);

            System.out.println("TABLE CREATED SUCCESSFULLY.");
        } catch (SQLException e) {
            System.out.println("ERROR CREATING TABLE "+e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, statement);
        }
    }

    /**
     * Method to delete a customer from the customer_table by ID.
     *
     * @param id             ID of the customer to delete
     * @param messageLabel   Label to display success or error message
     * @param textFieldForId TextField for the customer ID input
     */
    public static void deleteCustomerByID(int id, Label messageLabel, TextField textFieldForId) {

        try {
            connection = DataBaseConnection.getDataBaseConnection();

            // Validate id before making a delete request
            if (!validID(id)) {
                messageLabel.setText("ID MUST BE A POSITIVE INTEGER");
                return;
            }

            String sql = "DELETE FROM customer_table WHERE id = " + id;
            statement = connection.createStatement();
            int rowEffected = statement.executeUpdate(sql);

            // Check if the deletion was successful
            if (rowEffected > 0) {
                messageLabel.setText("CUSTOMER DELETED SUCCESSFULLY, ID: " + id);
            } else {
                messageLabel.setText("CUSTOMER NOT FOUND, ID: " + id);
            }
            textFieldForId.clear();
        } catch (SQLException ex) {
            System.out.println("ERROR DELETING CUSTOMER" + ex);
            messageLabel.setText("ERROR DELETING CUSTOMER, ID: " + id);
        } finally {
            closeConnection(connection, statement);

        }
    }

    /**
     * Method to add a new customer to the customer_table.
     *
     * @param id             ID of the new customer
     * @param phoneNumber    Phone number of the new customer
     * @param firstName      First name of the new customer
     * @param lastName       Last name of the new customer
     * @param location       Location of the new customer
     * @param messageLabel   Label to display success or error message
     * @param textFieldForId TextField for the customer ID input
     */
    public static void addNewCustomer(int id, int phoneNumber, String firstName, String lastName, String location, Label messageLabel, TextField textFieldForId) {

        try {
            connection = DataBaseConnection.getDataBaseConnection();

            // use parameterized SQL statement to prevent SQL injection
            String sql = "INSERT INTO customer_table (id, firstName, lastName, phoneNumber, location) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, phoneNumber);
            preparedStatement.setString(5, location);

            int rowEffected = preparedStatement.executeUpdate();

            messageLabel.setText("CUSTOMER ADDED SUCCESSFULLY");

        } catch (SQLIntegrityConstraintViolationException e) {
            messageLabel.setText("ID NUMBER ALREADY EXISTS");
            textFieldForId.clear();
        } catch (SQLException e) {
            System.out.println("ERROR ADDING CUSTOMER "+e);

        } finally {
            // close the resources in the reverse order they were opened
            closeConnection(connection, preparedStatement);
        }
    }

    /**
     * Retrieves all customers from the database and returns the result as a list of Customer objects.
     *
     * @return an ArrayList of Customer objects, each representing a row in the customer_table.
     */
    public static ArrayList<Customer> getAllCustomersFromTheDB() {

        ArrayList<Customer> customerList = new ArrayList<>();

        try {
            connection = DataBaseConnection.getDataBaseConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM customer_table");

            while (result.next()) {
                customerList.add(new Customer(
                        result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getInt("phoneNumber"),
                        result.getString("location")));
            }
        } catch (SQLException e) {
            System.out.println("ERROR RETRIEVING DATA "+e);
        } finally {
            DataBaseConnection.closeConnection(connection, statement, result);
        }

        return customerList;
    }

    /**
     * Retrieves the customer with the specified ID from the database and returns the result as a list of Customer objects.
     *
     * @param id the ID of the customer to retrieve.
     * @return an ArrayList of Customer objects, each representing a row in the customer_table with the specified ID.
     */
    public static ArrayList<Customer> getCustomersById(int id) {

        ArrayList<Customer> customerList = new ArrayList<>();

        try {
            connection = DataBaseConnection.getDataBaseConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM customer_table WHERE id = " + id);

            while (result.next()) {
                customerList.add(new Customer(
                        result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getInt("phoneNumber"),
                        result.getString("location")));
            }
        } catch (SQLException e) {
            System.out.println("ERROR RETRIEVING DATA "+e);
        } finally {
            DataBaseConnection.closeConnection(connection, statement, result);
        }

        return customerList;
    }
}