package com.nidhal.model;

public class Customer {

    //id should be exactly 8 numbers
    private int id;

    //first name should be less than 16 characters
    private String firstName;

    //last name should be less than 16 characters
    private String lastName;

    //phone number should be exactly 8 numbers
    private int phoneNumber;

    //location should be less than 32 characters
    private String location;

    //constructor with all attributes.
    public Customer(int id, String firstName, String lastName, int phoneNumber, String location) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }


    // this method used for verifying where the id/phoneNumber is valid or not.
    static boolean validID(int ID) {
        boolean valid = true;
        try {
            int idLength = String.valueOf(ID).length();
            System.out.println("idLength = " + idLength);
            if (idLength != 8) throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            System.out.println("Your Id/Phone must be = 8");
            valid = false;
        }

        return valid;
    }

    // this method used for verifying where the firstName/lastName is valid or not by setting the limit 16.
    // this method used for verifying where the location is valid or not by setting the limit 32.

    public static boolean validStrings(String field, int limit) {
        boolean valid = true;
        try {
            int idLength = field.length();
            if (idLength > limit) throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            System.out.println(field + " must be less than " + limit);
            valid = false;
        }
        return valid;
    }


    //  this method used in AddCustomerController so we verify all the fields when we are adding a customer.
    public static boolean validFields(int id, int phoneNumber, String firstName, String lastName, String location) {


        if (!(validID(id) && validID(phoneNumber) && validStrings(firstName, 16) && validStrings(lastName, 16) && validStrings(location, 32)))
            return false;
        else return true;
    }



    // getters & setters.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
