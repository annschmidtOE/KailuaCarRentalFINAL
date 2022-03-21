package com.company;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalClass {

    private final String USER = "kailua@kailuacarrental";
    private final String PASSWORD = "Gruppe4#";
    private final String DATABASE_URL = "jdbc:mysql://kailuacarrental.mysql.database.azure.com:3306/kailua_car_rental";

    public void updateRentalContract() throws SQLException {
        String attributeToUpdate = attributeToUpdate();
        int ID = rentalID();
        startUpdate(attributeToUpdate, ID);
    }

    public void startUpdate(String attributeToUpdate, int rentalID) throws SQLException {
        Scanner scan = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Enter the new data for the " + attributeToUpdate);
        String updatedData;
        updatedData = scan.next();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rental_contract SET " + attributeToUpdate +
                " = '" + updatedData + "'" +
                "WHERE rental_contract_id=" + rentalID + ";");
        preparedStatement.executeUpdate();
        System.out.println("Update executed");
        connection.close();
    }

    public String attributeToUpdate() {
        Scanner scan = new Scanner(System.in);
        String attributeToUpdate = null;
        int answer;
        System.out.println("What do you wish to update?\n1. To Date\n2. Km driven when returned");
        answer = scan.nextInt();
        switch (answer) {
            case 1: attributeToUpdate = "to_date"; break;
            case 2: attributeToUpdate = "km_driven_returned"; break;
        }
        return attributeToUpdate;
    }

    public int rentalID() throws SQLException {
        Scanner scan = new Scanner(System.in);
        printtRentalContracts();
        System.out.println("Enter the rental contract ID of the contract you wish to update");
        int ID = scan.nextInt();
        return ID;
    }

    public void addRentalContract() throws SQLException {
        System.out.println("Enter rental contract information: ");
        int customerID;
        int carID;
        String fromDate;
        String toDate;
        Scanner scan = new Scanner(System.in);
        System.out.println("Customer ID: ");
        customerID = scan.nextInt();
        System.out.println("Car ID:");
        carID = scan.nextInt();
        System.out.println("From date: ");
        fromDate = scan.next();
        System.out.println("To date: ");
        toDate = scan.next();
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO rental_contract VALUES(DEFAULT ," + customerID + "," + carID + ", '" + fromDate + "', '" + toDate + "', DEFAULT)");
        connection.close();
    }

    public void deleteRentalContract() throws SQLException {
        Scanner scan = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        printtRentalContracts();
        int carID;
        System.out.println("Enter the ID of the car you wish to delete");
        carID = scan.nextInt();
        //lav en print
        Statement statement = connection.createStatement();
        String sql = ("DELETE FROM rental_contract WHERE rental_contract_id=" + carID);
        statement.executeUpdate(sql);
        System.out.println("Rental contract deleted ..");
        System.out.println("Database after deletion: ");
        printtRentalContracts();
        Controller controller = new Controller();
        controller.rentalContracts();
    }

    public void printtRentalContracts() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT * FROM rental_contract");
        while (resultSet.next()) {
            String rentalContractID = resultSet.getString("rental_contract_id");
            String customerID = resultSet.getString("customer_customer_id");
            String carID = resultSet.getString("cars_car_id");
            String fromDate = resultSet.getString("from_date");
            String toDate = resultSet.getString("to_date");
            String kmDrivenReturned = resultSet.getString("km_driven_returned");
            System.out.println("rental_contract_id=" + rentalContractID + " customer_id=" + customerID + " carID=" + carID + " from_date=" + fromDate + " to_date="
                    + toDate + " km_driven_when_returned=" + kmDrivenReturned);
        }
        connection.close();
        System.out.println("");
    }


    public void customerDetails() throws SQLException {
        int customerID = rentalIDToCustomerID();
        System.out.println("Customer:");
        customerDetailsFromRental(customerID);
    }

    public void carDetails()throws SQLException{
        int carID = rentalIDToCarID();
        System.out.println("Car:");
        carDetailsFromRental(carID);
    }


    public int rentalIDToCarID()throws SQLException{
        int rentalID = getRentalContractID();
        int carID = 0;
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT * FROM rental_contract WHERE rental_contract_id="+rentalID+";");
        while (resultSet.next()) {
            String customerID = resultSet.getString("cars_car_id");
            carID=Integer.parseInt(customerID);
        }
        connection.close();
        return carID;
    }

    public int rentalIDToCustomerID()throws SQLException{
        int rentalID = getRentalContractID();
        int customerID = 0;
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT * FROM rental_contract WHERE rental_contract_id="+rentalID+";");
        while (resultSet.next()) {
            String customerID1 = resultSet.getString("customer_customer_id");
            customerID=Integer.parseInt(customerID1);
        }
        connection.close();
        return customerID;
    }

    public void carDetailsFromRental(int carID1)throws SQLException{
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT * FROM cars WHERE car_id="+carID1+";");
        while (resultSet.next()) {
            String brand = resultSet.getString("brand");
            String carID = resultSet.getString("car_id");
            String model = resultSet.getString("model");
            String fuel = resultSet.getString("fuel");
            String horsepower = resultSet.getString("horse_power");
            String ccm = resultSet.getString("ccm");
            String seats = resultSet.getString("seats");
            String leatherSeats = resultSet.getString("leather_seats");
            String airCondition = resultSet.getString("air_condition");
            String cruiseControl = resultSet.getString("cruise_control");
            String availability = resultSet.getString("car_availability");
            String registrationNumber = resultSet.getString("registration_number");
            String kmDriven = resultSet.getString("km_driven");
            String carPrinter = ("CarID="+carID+" car="+brand+" "+model+" fuel="+fuel+" horsepower="+horsepower+" ccm="+ccm
                    +"seats="+seats+" leather-seats="+leatherSeats+" air-condition="+airCondition+" cruise-control="+cruiseControl
                    +" registration-number="+registrationNumber+" km driven="+kmDriven+" availability="+availability);
            System.out.println(carPrinter);
        }
        connection.close();  }

    public void customerDetailsFromRental(int customerIDRental)throws SQLException{
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT * FROM customer WHERE customer_id="+customerIDRental+";");
        while (resultSet.next()) {
            String customerID = resultSet.getString("customer_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String zipCode = resultSet.getString("zip_code");
            String city = resultSet.getString("city");
            String email = resultSet.getString("email");
            String driverLicenceNumber = resultSet.getString("driver_licence_number");
            String driverSince = resultSet.getString("driver_since_date");
            String customerPrinter=("CustomerID="+customerID+" name="+firstName+" "+lastName+" city="+zipCode+" "+city
                    +" mail="+email+" driver-licence-number="+driverLicenceNumber+" driver-since="+driverSince);
            System.out.println(customerPrinter);
        }
        connection.close();
    }


   public int getRentalContractID()throws SQLException{
       int rentalContractID1;
       Scanner scanner = new Scanner(System.in);
       printtRentalContracts();
       System.out.println("Enter the rental_contract_id of the rental contract you wish to see more information about");
       rentalContractID1 = scanner.nextInt();

       System.out.println("Rental contract:");
       Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
       Statement statement = connection.createStatement();
       ResultSet resultSet;
       resultSet = statement.executeQuery("SELECT * FROM rental_contract WHERE rental_contract_id="+rentalContractID1+";");
       while (resultSet.next()) {
           String rentalContractID = resultSet.getString("rental_contract_id");
           String customerID = resultSet.getString("customer_customer_id");
           String carID = resultSet.getString("cars_car_id");
           String fromDate = resultSet.getString("from_date");
           String toDate = resultSet.getString("to_date");
           String kmDrivenReturned = resultSet.getString("km_driven_returned");
           System.out.println("rental_contract_id=" + rentalContractID + " customer_id=" + customerID + " carID=" + carID + " from_date=" + fromDate + " to_date="
                   + toDate + " km_driven_when_returned=" + kmDrivenReturned);
       }
       connection.close();
       return rentalContractID1;
   }


}
