package com.company;

import java.sql.*;
import java.util.Scanner;

public class CarClass {

    private final String USER = "kailua@kailuacarrental";
    private final String PASSWORD = "Gruppe4#";
    private final String DATABASE_URL = "jdbc:mysql://kailuacarrental.mysql.database.azure.com:3306/kailua_car_rental";
    private final UI ui = new UI();

    public void deleteCar() throws SQLException {
        Scanner scan = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        printCars();
        int carID;
        System.out.println("Enter the ID of the car you wish to delete");
        carID = scan.nextInt();
        Statement statement = connection.createStatement();
        String sql = ("DELETE FROM cars WHERE car_id="+carID);
        statement.executeUpdate(sql);
        System.out.println("Car deleted ..");
        Controller controller = new Controller();
        controller.cars(); }

    public void printCars() throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        Statement statement = con.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT * \n" +
                "FROM cars ");
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
        con.close();  }

    public void updateCar()throws SQLException{
        String attributeToUpdate = attributeToUpdate();
        int ID = customerID();
        startUpdate(attributeToUpdate,ID);}

    public void startUpdate(String attributeToUpdate, int ID)throws SQLException{
        Scanner scan = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        System.out.println("Enter the new data for the "+attributeToUpdate);
        String updatedData;
        updatedData= scan.next();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cars SET "+attributeToUpdate +
                " = '"+updatedData+"'"+
                "WHERE car_id="+ID+";");
        preparedStatement.executeUpdate();
        connection.close(); }

    public String attributeToUpdate(){
        Scanner scan = new Scanner(System.in);
        String attributeToUpdate = null;
        ui.updateCar();
        int answer = scan.nextInt();
        switch (answer) {
            case 1: attributeToUpdate = "km_driven"; break;
            case 2: attributeToUpdate = "car_availability"; break;
            case 3: attributeToUpdate = "leather_seats"; break;
            case 4: attributeToUpdate = "air_condition"; break;
        } return attributeToUpdate; }

    public int customerID()throws SQLException{
        Scanner scan = new Scanner(System.in);
        printCars();
        System.out.println("Enter the carID of the car you wish to update");
        int ID = scan.nextInt();
        return ID; }

    public void addCar()throws SQLException{
        Scanner scan = new Scanner(System.in);
        String carType;
        String brand;
        String model;
        String horsePower;
        String fuel;
        String ccm;
        String gear;
        String seats;
        String cruiseControl;
        String registrationNumber;
        String leatherSeats;
        String airCondition;
        String firstRegistrationMonth;
        String kmDriven;

        System.out.println("Car type");
        carType=scan.next();
        System.out.println("brand");
        brand=scan.next();
        System.out.println("model");
        model=scan.next();
        System.out.println("horse power");
        horsePower=scan.next();
        System.out.println("ccm");
        ccm=scan.next();
        System.out.println("gear");
        gear=scan.next();
        System.out.println("cruise control");
        cruiseControl=scan.next();
        System.out.println("registration number");
        registrationNumber=scan.next();
        System.out.println("leather seats?");
        leatherSeats=scan.next();
        System.out.println("air condition");
        airCondition=scan.next();
        System.out.println("first registration month");
        firstRegistrationMonth=scan.next();
        System.out.println("km driven");
        kmDriven=scan.next();
        System.out.println("fuel type");
        fuel=scan.next();
        System.out.println("how many seats");
        seats=scan.next();

        Connection connection = DriverManager.getConnection(DATABASE_URL, USER,PASSWORD);
        Statement statement = connection.createStatement();

        statement.executeUpdate("INSERT INTO cars VALUES (DEFAULT,'" + carType + "','" + brand + "', '" +
                model + "','" + horsePower + "','" + ccm + "', '" + gear + "', '" +
                fuel + "','" + seats + "','" + leatherSeats + "','" + airCondition + "','" +
                cruiseControl + "', '" + registrationNumber + "', '" + firstRegistrationMonth + "', '" +
                kmDriven + "', DEFAULT)");
        connection.close();
        System.out.println("car added");

    }
}
