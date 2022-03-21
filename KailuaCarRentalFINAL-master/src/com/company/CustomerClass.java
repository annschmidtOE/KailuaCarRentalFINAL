package com.company;

import java.sql.*;
import java.util.Scanner;

public class CustomerClass {

    private final String USER = "kailua@kailuacarrental";
    private final String PASSWORD = "Gruppe4#";
    private final String DATABASE_URL = "jdbc:mysql://kailuacarrental.mysql.database.azure.com:3306/kailua_car_rental";
    private final UI ui = new UI();

    public void updateCustomer()throws SQLException{
        String attributeToUpdate = attributeToUpdate();
        int ID = customerID();
        startUpdate(attributeToUpdate,ID);}

    public void startUpdate(String attributeToUpdate, int ID)throws SQLException{
        Scanner scan = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        System.out.println("Enter the new data for the "+attributeToUpdate);
        String updatedData;
        updatedData= scan.next();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET "+attributeToUpdate +
                " = '"+updatedData+"'"+
                "WHERE customer_id="+ID+";");
        preparedStatement.executeUpdate();
        connection.close(); }

    public String attributeToUpdate(){
        Scanner scan = new Scanner(System.in);
        String attributeToUpdate = null;
        ui.updateCustomer();
        int answer = scan.nextInt();
        switch (answer) {
            case 1: attributeToUpdate = "zip"; break;
            case 2: attributeToUpdate = "city"; break;
            case 3: attributeToUpdate = "phone"; break;
            case 4: attributeToUpdate = "email"; break;
        } return attributeToUpdate; }

    public int customerID()throws SQLException{
        Scanner scan = new Scanner(System.in);
        selectCustomers();
        System.out.println("Enter the customerID of the customer you wish to update");
        int ID = scan.nextInt();
        return ID; }

    public void deleteCustomer() throws SQLException {
        Scanner scan = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        selectCustomers();
        int customerID;
        System.out.println("Enter the ID of the car you wish to delete");
        customerID = scan.nextInt();
        Statement statement = connection.createStatement();
        String sql = ("DELETE FROM customer WHERE customer_id="+customerID);
        statement.executeUpdate(sql);
        System.out.println("Rental contract deleted ..");
        Controller controller = new Controller();
        controller.customer(); }

    public void selectCustomers() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT * FROM customer"); //EXECUTE UPDATE
        while (resultSet.next()) {
            String customerID = resultSet.getString("customer_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String zipCode = resultSet.getString("zip_code");
            String city = resultSet.getString("city");
            String email = resultSet.getString("email");
            String driverLicenceNumber = resultSet.getString("driver_licence_number");
            String driverSince = resultSet.getString("driver_since_date");
            System.out.println("CustomerID="+customerID+" name="+firstName+" "+lastName+" city="+zipCode+" "+city
                    +" mail="+email+" driver-licence-number="+driverLicenceNumber+" driver-since="+driverSince);
        }
        connection.close();
    }

    public void addCustomer() throws SQLException {
        Scanner scan = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        System.out.println("Input customer information");
        String firstName;
        String lastName;
        String zip;
        String city;
        String phone;
        String mail;
        String driverLicenceNumber;
        String driverSinceDate;
        System.out.println("first name");
        firstName=scan.next();
        System.out.println("lastName");
        lastName = scan.next();
        System.out.println("zip code");
        zip=scan.next();
        System.out.println("city");
        city=scan.next();
        System.out.println("phone");
        phone=scan.next();
        System.out.println("email");
        mail=scan.next();
        System.out.println("driver licence number");
        driverLicenceNumber=scan.next();
        System.out.println("driver since date");
        driverSinceDate=scan.next();
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO customer VALUES "
                + "(DEFAULT,'" + firstName + "', '" + lastName + "', " + zip + ", '" + city + "', " + phone
                + ", '" + mail + "', " + driverLicenceNumber
                + ", '" + driverSinceDate + "')");
        connection.close();
    }




}
