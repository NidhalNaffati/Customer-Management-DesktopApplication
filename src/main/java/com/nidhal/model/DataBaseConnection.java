package com.nidhal.model;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

import static com.nidhal.model.Customer.validID;

/**
 * DataBaseConnection is a class that handles connecting to a MySQL database named "CustomerDB"
 * and performing CRUD operations on the "customer_table" using JDBC.
 * It contains methods for creating the database and table if they do not exist, adding new customers,
 * deleting customers by ID, and getting a connection to the database.
 */
public class DataBaseConnection {

    // Variables to store the database connection details
    static String databaseName = "CustomerDB";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName + "?";
    static String user = "root";
    static String password = "root";

    /**
     * Method to get a connection to the database.
     *
     * @return Connection object to the database
     * @throws SQLException if there is an error connecting to the database
     */
    public static Connection getDataBaseConnection() throws SQLException {
        Connection databaseConnection = DriverManager.getConnection(url, user, password);
        return databaseConnection;
    }

    /**
     * Method to create the database if it does not exist.
     */
    public static void createDataBaseIfNotExists() {
        // Open a connection
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, password);
            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS CustomerDB";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully.");
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to create the customer_table if it does not exist.
     */
    public static void createTableCustomerIfNotExists() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `CustomerDB`.`customer_table` ( `id` INT NOT NULL, `firstName` VARCHAR(16) NOT NULL, `lastName` VARCHAR(16) NOT NULL, `phoneNumber` INT(8) NULL, `location` VARCHAR(32) NOT NULL, PRIMARY KEY (`id`), UNIQUE INDEX `phoneNumber_UNIQUE` (`phoneNumber` ASC) VISIBLE);";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully.");
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

        Connection connection = null;
        Statement statement = null;


        if (validID(id))
            try {

                connection = DataBaseConnection.getDataBaseConnection();

                String sql = "delete from customer_table where id = " + "'" + id + "'";

                statement = connection.createStatement();

                int rowEffected = statement.executeUpdate(sql);

                messageLabel.setText("CUSTOMER DELETED SUCCESS " + id);

                textFieldForId.clear();

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                ex.getCause();
                messageLabel.setText("must be 8");

            } finally {
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
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DataBaseConnection.getDataBaseConnection();

            String info = "'" + id + "' , '" + firstName + "' , '" + lastName + "' , '  " + phoneNumber + "' , '" + location + " ' ";

            String sql = "insert into customer_table values (" + info + ") ";

            statement = connection.createStatement();

            int rowEffected = statement.executeUpdate(sql);

            messageLabel.setText("CUSTOMER ADDED SUCESSFULY");

        } catch (IllegalArgumentException E) {
            E.getCause();
        } catch (SQLIntegrityConstraintViolationException E) {
            messageLabel.setText("ID NUMBER EXEST");
            textFieldForId.clear();
        } catch (SQLException E) {
            E.printStackTrace();
        } catch (Exception E) {
            E.printStackTrace();
        } finally {
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
    }
}

