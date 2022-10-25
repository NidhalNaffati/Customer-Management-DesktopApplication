package com.nidhal.model;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

import static com.nidhal.model.Customer.validID;

public class DataBaseConnection {

    static String databaseName = "CustomerDB";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName + "?";
    static String user = "root";
    static String password = "rootroot";


    // getting the connection to the database.
    public static Connection getDataBaseConnection() throws SQLException {
        Connection databaseConnection = DriverManager.getConnection(url, user, password);
        return databaseConnection;
    }


    // avoiding java.sql.SQLSyntaxErrorException:
    // --> auto create database if not exists.
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


    // avoiding java.sql.SQLSyntaxErrorException:
    // --> auto create Customer Table if not exists.
    public static void createTableCustomerIfNotExists() {

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();

            String sql = """
                    CREATE TABLE IF NOT EXISTS `CustomerDB`.`customer_table` (
                     `id` INT NOT NULL,
                     `firstName` VARCHAR(16) NOT NULL,
                     `lastName` VARCHAR(16) NOT NULL,
                     `phoneNumber` INT(8) NULL,
                     `location` VARCHAR(32) NOT NULL,
                     PRIMARY KEY (`id`),
                     UNIQUE INDEX `phoneNumber_UNIQUE` (`phoneNumber` ASC) VISIBLE);""".indent(1);

            stmt.executeUpdate(sql);

            System.out.println("Table created successfully.");

        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



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
            try {statement.close();} catch (SQLException e) {e.printStackTrace();}
            try {connection.close();} catch (SQLException e) {e.printStackTrace();
            }
        }
    }
}

