package com.nidhal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
class DataBaseConnectionTest {

    @Mock
    Connection databaseConnection;

    static String databaseName = "CustomerDB";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName + "?";
    static String user = "root";
    static String password = "root";

    //create the method that gives a random number greater that 1000_0000 and less than 9999_9999
    public static int getRandomNumber() {
        int min = 1000_0000;
        int max = 9999_9999;
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return random_int;
    }


    @BeforeEach
    void setUp() throws SQLException {
        databaseConnection = DriverManager.getConnection(url, user, password);
    }


    @Test
    void testDataBaseConnectivity() throws SQLException {
        assertNotNull(databaseConnection);
        assertTrue(databaseConnection.isValid(0));
        databaseConnection.close();
    }

    @Test
    public void testGetDataBaseConnection() {
        try {
            Connection conn = DataBaseConnection.getDataBaseConnection();
            assertNotNull(conn);
        } catch (SQLException e) {
            fail("Error connecting to the database");
        }
    }

    @Test
    public void testCreateDataBaseIfNotExists() {
        DataBaseConnection.createDataBaseIfNotExists();
        // Check if the database was created by trying to connect to it
        try {
            Connection conn = DataBaseConnection.getDataBaseConnection();
            assertNotNull(conn);
        } catch (SQLException e) {
            fail("Error connecting to the database");
        }
    }

    @Test
    public void testCreateTableCustomerIfNotExists() {
        DataBaseConnection.createTableCustomerIfNotExists();
        // Check if the table was created by trying to execute a SELECT statement
        try {
            Connection conn = DataBaseConnection.getDataBaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer_table");
            assertNotNull(rs);
        } catch (SQLException e) {
            fail("Error creating table or executing query");
        }
    }

    private void addCustomer() {
        try {
            Connection conn = DataBaseConnection.getDataBaseConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO customer_table (id, firstName, lastName, phoneNumber, location) VALUES (2, 'John', 'Doe', 87654322, 'Tunisia')");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Error adding customer");
        }
    }




    @Test
    public void testDeleteCustomerByID() {
        addCustomer();
        // Verify that the customer was added
        try {
            Connection conn = DataBaseConnection.getDataBaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer_table WHERE id = 1");
            assertTrue(rs.next());
            // Delete the customer
            DataBaseConnection.deleteCustomerByID(1, null, null);
            // Verify that the customer was deleted
            rs = stmt.executeQuery("SELECT * FROM customer_table WHERE id = 1");
            assertFalse(rs.next());
        } catch (SQLException e) {
            fail("Error deleting customer");
        }
    }
}