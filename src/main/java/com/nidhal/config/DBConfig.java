package com.nidhal.config;

/**
 * This class holds the database configuration details such as database name, URL, username, and password.
 */
public class DBConfig {
    private static final String DATABASE_NAME = "CustomerDB";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}