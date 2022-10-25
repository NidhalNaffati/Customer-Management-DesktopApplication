package com.nidhal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

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
    static String password = "rootroot";


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


}