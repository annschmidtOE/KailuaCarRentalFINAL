package com.company;

import java.sql.*;
import java.util.Scanner;

public class Controller {

    private final UI ui = new UI();

    public void selectDatabase() throws SQLException{
        Scanner scan = new Scanner(System.in);
        ui.selectDatabase();
        while (scan.hasNextInt()) {
            int choice = scan.nextInt();
            ui.selectDatabase();
            switch (choice) {
                case 1: cars();
                case 2: customer();
                case 3: rentalContracts();
                default:
                    System.out.println("invalid input \ntry again"); } } }

    public void rentalContracts()throws SQLException{
        Scanner scan = new Scanner(System.in);
        RentalClass rentalClass = new RentalClass();
        ui.rentalDeals();
        while (scan.hasNextInt()) {
            int choice = scan.nextInt();
            switch (choice) {
                case 1: rentalClass.printtRentalContracts(); rentalContracts(); break;
                case 2: rentalClass.addRentalContract(); rentalContracts();break;
                case 3: rentalClass.updateRentalContract(); rentalContracts(); break;
                case 4: rentalClass.deleteRentalContract(); break;
                case 5: rentalClass.customerDetails(); rentalContracts(); break;
                case 6: rentalClass.carDetails(); rentalContracts(); break;
                case 9: selectDatabase();
                default:
                    System.out.println("invalid input\n try again"); } } }

    public void customer() throws SQLException{
        Scanner scan = new Scanner(System.in);
        CustomerClass customerClass = new CustomerClass();
        ui.customers();
        while (scan.hasNextInt()) {
            int choice = scan.nextInt();
            switch (choice) {
                case 1: customerClass.selectCustomers(); customer(); break;
                case 2: customerClass.addCustomer(); customer(); break;
                case 3: customerClass.updateCustomer(); customer(); break;
                case 4: customerClass.deleteCustomer(); customer(); break;
                case 9: selectDatabase();
                default:
                    System.out.println("invalid input\n try again");
            } } }

    public void cars()throws SQLException {
        Scanner scan = new Scanner(System.in);
        CarClass carClass = new CarClass();
        ui.cars();
        while (scan.hasNextInt()) {
            int choice = scan.nextInt();
            switch (choice) {
                case 1: carClass.printCars(); cars(); break;
                case 2: carClass.addCar(); cars(); break;
                case 3: carClass.updateCar(); cars(); break;
                case 4: carClass.deleteCar(); cars(); break;
                case 9: selectDatabase();
                default:
                    System.out.println("invalid input\n try again");
            } } }


    }


}
